package com.checkout51.challenge.feature.offers

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.checkout51.challenge.R
import com.checkout51.challenge.components.SortType
import com.checkout51.challenge.core.ContextManager
import com.checkout51.challenge.extensions.asMutable
import com.checkout51.challenge.extensions.toUsDollars
import com.checkout51.challenge.data.OffersRepository
import com.checkout51.challenge.data.remote.Offer
import kotlinx.coroutines.launch

class OffersViewModel(
    private val offersRepository: OffersRepository,
    private val contextManager: ContextManager
) : ViewModel() {

    private var offers = emptyList<Offer>()

    val viewState: LiveData<OffersViewState> = MutableLiveData()

    fun sortOffersBy(sortType: SortType) {
        val sortedOffers = this.offers.sortedWith { offer1, offer2 ->
            if (sortType == SortType.NAME) {
                offer1.name?.compareTo(offer2.name ?: "") ?: 0
            } else {
                offer1.cashBack?.compareTo(offer2.cashBack ?: 0.0) ?: 0
            }
        }
        viewState.asMutable().value = OffersViewState.ShowOffers(sortedOffers.mapToOfferViewItem())
    }

    fun fetchOffers() = viewModelScope.launch {
        viewState.asMutable().value = OffersViewState.ShowLoading

        val state = offersRepository.getOffers().fold(
            ::handleSuccessOffers,
            ::handleErrorOffers
        )

        viewState.asMutable().value = OffersViewState.HideLoading
        viewState.asMutable().value = state
    }

    private fun handleSuccessOffers(offers: List<Offer>): OffersViewState {
        return if (offers.isEmpty()) {
            OffersViewState.ShowMessage(
                contextManager.getString(R.string.no_offers_found)
            )
        } else {
            this.offers = offers
            OffersViewState.ShowOffers(offers.mapToOfferViewItem())
        }
    }

    private fun List<Offer>.mapToOfferViewItem(): List<OfferViewItem> {
        return this.map {
            val cashBack = contextManager.getString(
                R.string.cash_back_value,
                it.cashBack?.toUsDollars() ?: "$ 0.00"
            )
            OfferViewItem(
                id = it.id ?: "",
                image = it.imageUrl ?: "",
                name = it.name ?: "",
                cashBack = cashBack
            )
        }
    }

    private fun handleErrorOffers(throwable: Throwable): OffersViewState {
        return OffersViewState.ShowMessage(
            contextManager.getString(R.string.error_while_loading_offers)
        )
    }
}
package com.checkout51.challenge.feature.offers

sealed class OffersViewState {
    data class ShowOffers(
        val offers: List<OfferViewItem>
    ) : OffersViewState()
    data class ShowMessage(
        val message: String
    ) : OffersViewState()
    object ShowLoading : OffersViewState()
    object HideLoading : OffersViewState()
}

data class OfferViewItem(
    val id: String,
    val name: String,
    val image: String,
    val cashBack: String?
)
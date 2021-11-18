package com.checkout51.challenge.feature.offers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.checkout51.challenge.R
import com.checkout51.challenge.core.ContextManager
import com.checkout51.challenge.coretest.CoroutineTestRule
import com.checkout51.challenge.data.OffersRepository
import com.checkout51.challenge.data.remote.Offer
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class OffersViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()

    private val offersRepositoryMock = mockk<OffersRepository>()
    private val contextManagerMock = mockk<ContextManager>()

    @Test
    fun init_should_fetchOffers_successfully() {
        // Given
        val states = mutableListOf<OffersViewState>()
        val offers = listOf(
            Offer(
                id = "1",
                name = "test",
                imageUrl = "",
                cashBack = 1.0
            )
        )
        coEvery { offersRepositoryMock.getOffers() } returns Result.success(offers)
        every { contextManagerMock.getString(R.string.cash_back_value, "$1.00") } returns "$1.00 Cash Back"

        // When
        val viewModel = OffersViewModel(offersRepositoryMock, contextManagerMock)
        viewModel.viewState.observeForever {
            states += it
            print("event: $it")
        }
        viewModel.fetchOffers()

        // Then
        assertTrue(states[0] is OffersViewState.ShowLoading)
        assertTrue(states[1] is OffersViewState.HideLoading)
        assertTrue(states[2] is OffersViewState.ShowOffers)
        val offersResult = (states[2] as OffersViewState.ShowOffers).offers
        assertEquals(listOf(OfferViewItem("1", "test", "", "$1.00 Cash Back")), offersResult)
    }

    @Test
    fun init_should_fetch_empty_offers() {
        // Given
        coEvery { offersRepositoryMock.getOffers() } returns Result.success(listOf())
        every { contextManagerMock.getString(R.string.no_offers_found) } returns "no offers"

        // When
        val viewModel = OffersViewModel(offersRepositoryMock, contextManagerMock)
        viewModel.fetchOffers()

        // Then
        assertTrue(viewModel.viewState.value is OffersViewState.ShowMessage)
        val message = (viewModel.viewState.value as OffersViewState.ShowMessage).message
        assertEquals("no offers", message)
    }

    @Test
    fun init_should_fetch_orders_and_throw_error() {
        // Given
        coEvery { offersRepositoryMock.getOffers() } returns Result.failure(Exception())
        every { contextManagerMock.getString(R.string.error_while_loading_offers) } returns "error"

        // When
        val viewModel = OffersViewModel(offersRepositoryMock, contextManagerMock)
        viewModel.fetchOffers()

        // Then
        assertTrue(viewModel.viewState.value is OffersViewState.ShowMessage)
        val message = (viewModel.viewState.value as OffersViewState.ShowMessage).message
        assertEquals("error", message)
    }
}
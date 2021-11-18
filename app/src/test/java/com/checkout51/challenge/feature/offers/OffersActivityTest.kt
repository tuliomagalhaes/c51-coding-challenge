package com.checkout51.challenge.feature.offers

import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.checkout51.challenge.Checkout21TestApp
import com.checkout51.challenge.components.SortType
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = Checkout21TestApp::class, instrumentedPackages = ["androidx.loader.content"])
class OffersActivityTest : KoinTest {

    private val offersRobotScreen = OffersRobotScreen()

    private val viewStateLiveDataObserver = slot<Observer<OffersViewState>>()
    private val viewModelMock = mockk<OffersViewModel>(relaxed = true)

    @Before
    fun setUp() {
        startKoin{
            modules(
                module {
                    viewModel { viewModelMock }
                }
            )
        }

        every { viewModelMock.viewState.observe(any(), capture(viewStateLiveDataObserver)) } just Runs
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun offerActivity_should_show_offer_list_when_state_is_ShowOffers() {
        // Given
        val offers = listOf(
            OfferViewItem(
                id = "1",
                name = "test",
                image = "",
                cashBack = "$1.00"
            )
        )
        offersRobotScreen.launch()

        // When
        viewStateLiveDataObserver.captured.onChanged(OffersViewState.ShowOffers(offers))

        // Then
        offersRobotScreen
            .matchOfferViewItemAtPosition(0, offers[0])
    }

    @Test
    fun offerActivity_should_send_SortType_NAME_to_OfferViewModel_when_name_button_is_clicked() {
        // Given
        offersRobotScreen.launch()

        // When
        offersRobotScreen.clickSortByNameButton()

        // Then
        verify(exactly = 1) { viewModelMock.sortOffersBy(SortType.NAME) }
    }

    @Test
    fun offerActivity_should_send_SortType_CASH_BACK_to_OfferViewModel_when_cash_back_button_is_clicked() {
        // Given
        offersRobotScreen.launch()

        // When
        offersRobotScreen.clickSortByCashBackButton()

        // Then
        verify(exactly = 1) { viewModelMock.sortOffersBy(SortType.CASH_BACK) }
    }

    @Test
    fun offerActivity_should_show_message_when_state_is_ShowMessage() {
        // Given
        val message = "Test"
        offersRobotScreen.launch()

        // When
        viewStateLiveDataObserver.captured.onChanged(OffersViewState.ShowMessage(message))

        // Then
        offersRobotScreen
            .matchTextMessage(message)
            .matchTextVisibility(true)
    }

    @Test
    fun offerActivity_should_show_progress_when_state_is_ShowLoading() {
        // Given
        offersRobotScreen.launch()

        // When
        viewStateLiveDataObserver.captured.onChanged(OffersViewState.ShowLoading)

        // Then
        offersRobotScreen
            .matchProgressBarVisibility(true)
    }

    @Test
    fun offerActivity_should_hide_progress_when_state_is_HideLoading() {
        // Given
        offersRobotScreen.launch()

        // When
        viewStateLiveDataObserver.captured.onChanged(OffersViewState.HideLoading)

        // Then
        offersRobotScreen
            .matchProgressBarVisibility(false)
    }
}
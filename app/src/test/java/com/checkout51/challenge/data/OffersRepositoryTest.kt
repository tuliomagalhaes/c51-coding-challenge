package com.checkout51.challenge.data

import com.checkout51.challenge.core.IOHandler
import com.checkout51.challenge.data.remote.Offer
import com.checkout51.challenge.data.remote.OffersApi
import com.checkout51.challenge.data.remote.OffersResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Test

class OffersRepositoryTest {

    private val offersApiMock = mockk<OffersApi>()
    private val offersRepository = OffersRepository(offersApiMock, IOHandler())

    @Test
    fun getOffers_should_map_OffersResponse_to_Offer() = runBlocking {
        // Given
        val offers = listOf(Offer("1", "a", "", 1.0))
        val offersResponse = OffersResponse(0, offers)
        coEvery { offersApiMock.getOffers() } returns offersResponse

        // When
        val result = offersRepository.getOffers()

        // Then
        assertEquals(offers, result.getOrNull())
    }
}
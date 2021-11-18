package com.checkout51.challenge.data

import com.checkout51.challenge.core.IOHandler
import com.checkout51.challenge.data.remote.Offer
import com.checkout51.challenge.data.remote.OffersApi

class OffersRepository(
    private val offersApi: OffersApi,
    private val ioHandler: IOHandler
) {

    suspend fun getOffers(): Result<List<Offer>> = ioHandler.run {
        offersApi.getOffers()
    }.map {
        it.offers
    }
}
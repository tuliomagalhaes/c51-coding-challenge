package com.checkout51.challenge.data.remote

import retrofit2.http.GET

interface OffersApi {

    @GET("0fb06235-0f4c-4a3e-ad56-0b2301dc80d8")
    suspend fun getOffers(): OffersResponse
}
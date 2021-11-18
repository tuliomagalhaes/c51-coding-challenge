package com.checkout51.challenge.di

import com.checkout51.challenge.data.remote.OffersApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    factory { get<Retrofit>().create(OffersApi::class.java) }
}
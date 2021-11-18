package com.checkout51.challenge.di

import com.checkout51.challenge.data.OffersRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { OffersRepository(get(), get()) }
}
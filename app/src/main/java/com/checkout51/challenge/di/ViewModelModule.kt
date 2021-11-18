package com.checkout51.challenge.di

import com.checkout51.challenge.feature.offers.OffersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OffersViewModel(get(), get()) }
}
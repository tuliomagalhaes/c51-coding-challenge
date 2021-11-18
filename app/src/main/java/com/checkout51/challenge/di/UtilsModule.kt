package com.checkout51.challenge.di

import com.checkout51.challenge.core.ContextManager
import com.checkout51.challenge.core.IOHandler
import org.koin.dsl.module

val utilsModule = module {
    factory { ContextManager(get()) }
    factory { IOHandler() }
}
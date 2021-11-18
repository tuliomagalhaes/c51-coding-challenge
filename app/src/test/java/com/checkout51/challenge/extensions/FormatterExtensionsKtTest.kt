package com.checkout51.challenge.extensions

import org.junit.Assert.*

import org.junit.Test

class FormatterExtensionsKtTest {

    @Test
    fun toUsDollars_should_format_in_US_dollars() {
        assertEquals("$100.00", 100.0.toUsDollars())
    }
}
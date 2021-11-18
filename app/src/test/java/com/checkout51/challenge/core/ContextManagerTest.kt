package com.checkout51.challenge.core

import android.content.Context
import com.checkout51.challenge.R
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ContextManagerTest {

    private val contextMock = mockk<Context>()
    private val contextManager = ContextManager(contextMock)

    @Test
    fun getString_should_call_context_getString() {
        // Given
        val string = "hello"
        every { contextMock.getString(R.string.error_while_loading_offers) } returns string

        // When
        val result = contextManager.getString(R.string.error_while_loading_offers)

        // Then
        assertEquals(string, result)
    }

    @Test
    fun getString_with_args_should_call_context_getString() {
        // Given
        val string = "hello"
        every { contextMock.getString(R.string.error_while_loading_offers, 1, 2) } returns string

        // When
        val result = contextManager.getString(R.string.error_while_loading_offers, 1, 2)

        // Then
        assertEquals(string, result)
    }
}
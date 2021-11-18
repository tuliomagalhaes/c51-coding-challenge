package com.checkout51.challenge.core

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class IOHandlerTest {

    private val ioHandler = IOHandler()

    @Before
    fun setUp() {
    }

    @Test
    fun run_should_return_Result_Success() = runBlocking {
        // Given
        val value = "hello"

        // When
        val result = ioHandler.run {
            value
        }

        // Then
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), value)
    }

    @Test
    fun run_should_return_Result_Failure() = runBlocking {
        // Given
        val value = IllegalStateException()

        // When
        val result = ioHandler.run {
            throw value
        }

        // Then
        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), value)
    }
}
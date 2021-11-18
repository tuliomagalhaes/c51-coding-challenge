package com.checkout51.challenge.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class IOHandler {

    suspend fun <T> run(block: suspend () -> T) = withContext(Dispatchers.IO) {
        try {
            Result.success(block())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
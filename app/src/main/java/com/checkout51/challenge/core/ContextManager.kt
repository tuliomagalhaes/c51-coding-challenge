package com.checkout51.challenge.core

import android.content.Context

class ContextManager(
    private val context: Context
) {

    fun getString(stringRes: Int) = context.getString(stringRes)

    fun getString(stringRes: Int, vararg args: Any) = String.format(context.getString(stringRes, *args))
}
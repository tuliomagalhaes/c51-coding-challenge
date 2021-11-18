package com.checkout51.challenge.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.toUsDollars(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.CANADA)
    return format.format(this)
}
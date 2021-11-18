package com.checkout51.challenge.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.asMutable() = this as MutableLiveData<T>
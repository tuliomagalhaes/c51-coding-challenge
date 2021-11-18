package com.checkout51.challenge.core

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

abstract class DiffUtilCallback<T> : DiffUtil.ItemCallback<T>() {
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem?.equals(newItem) == true
}
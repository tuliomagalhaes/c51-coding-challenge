package com.checkout51.challenge.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.checkout51.challenge.databinding.SortViewBinding

typealias OnSortSelected = (SortType) -> Unit

enum class SortType {
    NAME,
    CASH_BACK
}

class SortView : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    private val binding: SortViewBinding = SortViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )
    private var onSortSelected: OnSortSelected? = null

    init {
        binding.btSortByName.setOnClickListener {
            this.onSortSelected?.invoke(SortType.NAME)
        }
        binding.btSortByCashBack.setOnClickListener {
            this.onSortSelected?.invoke(SortType.CASH_BACK)
        }
    }

    fun onSortTypeChanged(callback: OnSortSelected) {
        this.onSortSelected = callback
    }
}
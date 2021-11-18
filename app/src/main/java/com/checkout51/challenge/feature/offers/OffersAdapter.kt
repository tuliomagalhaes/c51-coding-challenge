package com.checkout51.challenge.feature.offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.checkout51.challenge.core.DiffUtilCallback
import com.checkout51.challenge.databinding.OfferItemViewBinding

private class ProductsDiffUtilCallback : DiffUtilCallback<OfferViewItem>() {
    override fun areItemsTheSame(oldItem: OfferViewItem, newItem: OfferViewItem): Boolean {
        return oldItem.id == newItem.id
    }
}

class OffersAdapter : ListAdapter<OfferViewItem, ProductViewHolder>(ProductsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(OfferItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

class ProductViewHolder(
    private val binding: OfferItemViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(offer: OfferViewItem) {
        binding.ivOffer.load(offer.image)
        binding.tvName.text = offer.name
        binding.tvCashBack.text = offer.cashBack
    }
}
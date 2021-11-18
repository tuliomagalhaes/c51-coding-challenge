package com.checkout51.challenge.data.remote

import com.google.gson.annotations.SerializedName

data class OffersResponse(
    @field:SerializedName("batch_id")
    val batchId: Int,
    @field:SerializedName("offers")
    val offers: List<Offer>
)
package com.checkout51.challenge.data.remote

import com.google.gson.annotations.SerializedName

data class Offer(
    @field:SerializedName("offer_id")
    val id: String?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("image_url")
    val imageUrl: String?,
    @field:SerializedName("cash_back")
    val cashBack: Double?
)
package com.example.plaplixapp.model.remote.pojo


import com.google.gson.annotations.SerializedName

data class Plaplix(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)
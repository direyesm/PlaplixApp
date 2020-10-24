package com.example.plaplixapp.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "detail_table")
class PlapixdetallEnti (val credit: Boolean,
                        val description: String,
                        @PrimaryKey val id: Int,
                        val image: String,
                        val lastPrice: Int,
                        val name: String,
                        val price: Int
)
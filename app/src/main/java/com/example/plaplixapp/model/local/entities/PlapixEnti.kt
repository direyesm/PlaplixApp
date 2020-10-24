package com.example.plaplixapp.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "cell_table")
class PlapixEnti (@PrimaryKey val id: Int,
                  val image: String,
                  val name: String,
                  val price: Int
)
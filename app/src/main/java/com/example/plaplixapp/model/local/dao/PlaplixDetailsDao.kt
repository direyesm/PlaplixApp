package com.example.plaplixapp.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plaplixapp.model.local.entities.PlapixdetallEnti


@Dao
interface PlaplixDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetails(listdetails: List<PlapixdetallEnti>)

    @Query("SELECT * FROM detail_table")
    fun getAlldetailsFromDB(): LiveData<List<PlapixdetallEnti>>

    @Query("SELECT * FROM detail_table WHERE id =:id")
    fun getDetailsById(id: Int): LiveData<PlapixdetallEnti>
}
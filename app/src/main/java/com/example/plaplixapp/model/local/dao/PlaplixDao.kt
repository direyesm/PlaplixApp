package com.example.plaplixapp.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plaplixapp.model.local.entities.PlapixEnti
import com.example.plaplixapp.model.remote.pojo.Plaplix


@Dao
interface PlaplixDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAllCell(listcell: List<PlapixEnti>)

    @Query("SELECT * FROM cell_table")
    fun getlAllCellFromDB(): LiveData<List<PlapixEnti>>

    @Query("SELECT * FROM cell_table WHERE id =:id")
    fun getCellByID(id : Int): LiveData<PlapixEnti>
}
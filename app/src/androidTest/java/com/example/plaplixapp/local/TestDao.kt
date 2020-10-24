package com.example.plaplixapp.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.plaplixapp.model.local.PlaplixRoomDatabase
import com.example.plaplixapp.model.local.dao.PlaplixDao
import com.example.plaplixapp.model.local.entities.PlapixEnti
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mPlaplixDao: PlaplixDao
    private lateinit var db : PlaplixRoomDatabase

    @Before
    fun setUp(){
        val contex = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(contex, PlaplixRoomDatabase::class.java).build()
        mPlaplixDao = db.getCellDao()
    }

    @After
    fun showDown(){
        db.close()
    }

    @Test
    fun insertListElementCell_happy_case() = runBlocking {
        //given
        val cellList = listOf(PlapixEnti(1, "aaaaa", "aaaaa", 1500000))
        //when
        mPlaplixDao.insertAllCell(cellList)
        //then
        mPlaplixDao.gelAllCellFromDB().observeForever {
            assertThat(it.isNotEmpty())
            assertThat(it[0].id).isEqualTo("1")
            assertThat(it).hasSize(1)
        }
    }
}
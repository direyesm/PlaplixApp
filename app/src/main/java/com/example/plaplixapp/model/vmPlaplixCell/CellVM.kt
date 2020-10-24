package com.example.plaplixapp.model.vmPlaplixCell

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.plaplixapp.model.local.PlaplixRoomDatabase
import com.example.plaplixapp.model.local.entities.PlapixEnti

class CellVM(application: Application): AndroidViewModel(application) {
    private var cellRepo : CellRepo
    val liveDataFromLocal : LiveData<List<PlapixEnti>>

    init {
        val cellDao = PlaplixRoomDatabase.getDatabase(application).getCellDao()
        cellRepo = CellRepo(cellDao)
        cellRepo.getDataFromServer()
        liveDataFromLocal = cellRepo.mLiveData
    }

    fun getCellByID(id : Int): LiveData<PlapixEnti>{
        return cellRepo.obtainCellByID(id)
    }
}
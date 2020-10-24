package com.example.plaplixapp.model.vmPlaplixDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.plaplixapp.model.local.PlaplixRoomDatabase
import com.example.plaplixapp.model.local.entities.PlapixdetallEnti

class DetailsVM(application: Application): AndroidViewModel(application) {
    private var mRepository : DetailsRepo
    val liveDataFromLocal : LiveData<List<PlapixdetallEnti>>

    init {
        val detaDao = PlaplixRoomDatabase.getDatabase(application).getDetailsDao()
        mRepository = DetailsRepo(detaDao)
        mRepository.getDataFromServer()
        liveDataFromLocal = mRepository.mLiveData
    }

    fun getDetailsByID(id : Int): LiveData<PlapixdetallEnti>{
        return mRepository.getDetailsByID(id)
    }
}
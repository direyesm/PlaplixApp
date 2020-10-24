package com.example.plaplixapp.model.vmPlaplixDetails

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.plaplixapp.model.local.dao.PlaplixDetailsDao
import com.example.plaplixapp.model.local.entities.PlapixdetallEnti
import com.example.plaplixapp.model.remote.RetrofitClient
import com.example.plaplixapp.model.remote.pojo.Plaplixdetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsRepo(private val detailsDao: PlaplixDetailsDao) {
    
    private val service = RetrofitClient.retrofitCliente()
    val mLiveData = detailsDao.getAlldetailsFromDB()

    fun getDetailsByID(id: Int): LiveData<PlapixdetallEnti>{
        return detailsDao.getDetailsById(id)
    }


    fun getDataFromServer() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.fetchdetailsList() }
        service.onSuccess {
            when(it.code()){
                in 200..299 -> it.body()?.let {
                    detailsDao.insertAllDetails(converter(it))
                }
                in 300..599 -> Log.d("RESPONSE_300-", it.body().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }
        service.onFailure {

        }
    }
    fun converter(listDetails: List<Plaplixdetails>): List<PlapixdetallEnti>{
        var details = mutableListOf<PlapixdetallEnti>()
        details.map {
            details.add(
                PlapixdetallEnti(it.credit,
            it.description,
            it.id,
            it.image,
            it.lastPrice,
            it.name,
            it.price))
        }
        return details
    }

}
package com.example.plaplixapp.model.vmPlaplixCell

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.plaplixapp.model.local.dao.PlaplixDao
import com.example.plaplixapp.model.local.entities.PlapixEnti
import com.example.plaplixapp.model.remote.RetrofitClient
import com.example.plaplixapp.model.remote.pojo.Plaplix
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CellRepo(private val cellDao: PlaplixDao) {

    private val service = RetrofitClient.retrofitCliente()
    val mLiveData = cellDao.getlAllCellFromDB()

    fun obtainCellByID(id : Int): LiveData<PlapixEnti>{
        return cellDao.getCellByID(id)
    }

    fun getDataFromServer()= CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.fetchallCellcoru() }
        service.onSuccess {
            when(it.code()){
                in 200..299 -> it.body()?.let {
                    cellDao.insertAllCell(converter(it))
                }
                in 300..599 -> Log.d("RESPONSE_300-", it.body().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
            service.onFailure {
                Log.e("ERROR", it.message.toString())
            }
        }
    }

    fun converter(listCell: List<Plaplix>): List<PlapixEnti>{
        var cell = mutableListOf<PlapixEnti>()
        listCell.map {
            cell.add(
                PlapixEnti(it.id,
            it.image,
            it.name,
            it.price)
            )
        }
        return cell
    }
}
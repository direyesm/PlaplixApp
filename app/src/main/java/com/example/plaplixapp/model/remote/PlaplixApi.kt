package com.example.plaplixapp.model.remote

import com.example.plaplixapp.model.remote.pojo.Plaplixdetails
import com.example.plaplixapp.model.remote.pojo.Plaplix
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaplixApi {

    @GET("products/")
    suspend fun fetchallCellcoru(): Response<List<Plaplix>>

    @GET("/details/{id}")
    suspend fun fetchdetailsList() : Response<List<Plaplixdetails>>
}
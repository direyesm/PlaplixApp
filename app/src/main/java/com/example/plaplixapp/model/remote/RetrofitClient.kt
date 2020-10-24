package com.example.plaplixapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{

        private const val BASE_URL = "http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun retrofitCliente(): PlaplixApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PlaplixApi::class.java)
        }
    }
}
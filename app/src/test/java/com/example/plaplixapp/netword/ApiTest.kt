package com.example.plaplixapp.netword

import com.example.plaplixapp.model.local.entities.PlapixEnti
import com.example.plaplixapp.model.remote.PlaplixApi
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mPlaplixApi : PlaplixApi

    @Before
    fun setUp(){
        mMockWebServer = MockWebServer()
        val mRetrofic = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mPlaplixApi = mRetrofic.create(PlaplixApi::class.java)
    }

    @After
    fun shutDown(){
        mMockWebServer.shutdown()
    }


    @Test
    fun getAllCell_happy_case() = runBlocking {
        //given
        val mResultList = listOf(PlapixEnti(2,
        "https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/phones/nova7-se/img/mob/huawei-nova7-se-mob.png",
        "Huawei Nova 7 SE 128GB",347760))
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mResultList)))
        //when
        val result = mPlaplixApi.fetchallCellcoru()
        //then
        assertThat(result).isNotNull()
        val body = result.body()
        assertThat(body).hasSize(1)
        assertThat(body?.get(0)?.id).isEqualTo(1)
    }
}
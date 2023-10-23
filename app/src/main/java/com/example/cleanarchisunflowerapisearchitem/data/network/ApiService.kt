package com.example.cleanarchisunflowerapisearchitem.data.network

import com.example.cleanarchisunflowerapisearchitem.common.Constants
import com.example.cleanarchisunflowerapisearchitem.data.dto.PixabayDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getSearchImage(
    @Query("key") key: String = Constants.KEY,
    @Query("q") query: String
    ) : PixabayDto
}
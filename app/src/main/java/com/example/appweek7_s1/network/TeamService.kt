package com.example.appweek7_s1.network

import com.example.appweek7_s1.models.ApiResponseHeader
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.Call

interface TeamService {
    @GET("1341")
    fun getTeams(@Header("x-rapidapi-host")host : String,
        @Header("x-rapidapi-key")apiKey : String): Call<ApiResponseHeader>
}
package com.example.kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("http://34.133.121.100:3000/")
    fun getFreeClassrooms(
        @Query("dayOfWeek") dayOfWeek: String,
        @Query("startTime") startTime: String,
        @Query("endTime") endTime: String
    ): Call<List<Classroom>>
}

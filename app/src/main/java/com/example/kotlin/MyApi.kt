package com.example.kotlin

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {
    @POST("spaces")
    fun getFreeClassrooms(
        @Body jsonBody: JsonObject
    ): Call<List<Classroom>>
}

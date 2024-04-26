package com.example.kotlin
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyApplication : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        getFreeClassrooms("j", "0900", "1400")

        super.onCreate()
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }
}

fun getFreeClassrooms(dayOfWeek: String, startTime: String, endTime: String) {
    val jsonBody = JsonObject().apply {
        addProperty("dayOfWeek", dayOfWeek)
        addProperty("startTime", startTime)
        addProperty("endTime", endTime)
    }

    val api = Retrofit.Builder()
        .baseUrl("http://available-spaces-dot-unischedule-5ee93.uc.r.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)

    api.getFreeClassrooms(jsonBody).enqueue(object : Callback<List<Classroom>> {
        override fun onResponse(call: Call<List<Classroom>>, response: Response<List<Classroom>>) {
            if (response.isSuccessful) {
                val classrooms = response.body()
                Log.i("RESPUESTA", "onResponse: $classrooms ")
            } else {
                Log.i("RESPUESTA", "onResponse: Unsuccessful response, status code: ${response.code()} ")
            }
        }
        override fun onFailure(call: Call<List<Classroom>>, t: Throwable) {
            Log.i("RESPUESTA", "onFailure:${t.message} ")
        }
    })
}
package com.udacity.asteroidradar.api

import com.google.gson.JsonObject
import com.udacity.asteroidradar.util.Constants
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class AsteroidRepositoryService {

    val service: Service

    init {
        val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(Service::class.java)
    }

    interface Service {

        @GET("planetary/apod/")
        suspend fun getPicture(
            @Query("api_key")
            apiKey: String = Constants.API_KEY
        ): PictureOfDay

        @GET("neo/rest/v1/feed/")
        suspend fun getAsteroids(
            @Query("start_date")
            startDate: String,
            @Query("end_date")
            endDate: String,
            @Query("api_key")
            apiKey: String = Constants.API_KEY
        ): JsonObject

    }
}
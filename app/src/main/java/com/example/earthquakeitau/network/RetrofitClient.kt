package com.example.earthquakeitau

import com.example.earthquakeitau.model.EarthquakeResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakeService {
    @GET("earthquakes/usgs")
    suspend fun getEarthquakes(@Query("format") format: String, @Query("starttime") starttime: String, @Query("endtime") endtime: String): EarthquakeResponse

    companion object {
        val instance: EarthquakeService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(EarthquakeService::class.java)
        }
    }
}
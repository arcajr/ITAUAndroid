package com.example.earthquakeitau.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakeService {
    @GET("query")
    fun getEarthquakes(
        @Query("format") format: String = "geojson",
        @Query("limit") limit: Int = 10,
        @Query("minmag") minMag: Double = 5.0,
        @Query("orderby") orderBy: String = "time",
        @Query("dedupe") dedupe: Int = 1,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<String>
}
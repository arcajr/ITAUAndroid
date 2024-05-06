package com.example.earthquakeitau.repository

import android.location.Location
import com.example.earthquakeitau.model.EarthquakeResponse
import com.example.earthquakeitau.network.EarthquakeService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EarthquakeRepository(
    private val locationProviderClient: FusedLocationProviderClient
) {

    private val earthquakeService: EarthquakeService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        earthquakeService = retrofit.create(EarthquakeService::class.java)
    }

    fun getEarthquakes(callback: (Result<EarthquakeResponse>) -> Unit) {
        locationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                getEarthquakesNearby(location.latitude, location.longitude, callback)
            } else {
                callback(Result.failure(Throwable("Location not available")))
            }
        }.addOnFailureListener { exception ->
            callback(Result.failure(exception))
        }
    }

    private fun getEarthquakesNearby(
        latitude: Double,
        longitude: Double,
        callback: (Result<EarthquakeResponse>) -> Unit
    ) {
        val call = earthquakeService.getEarthquakes(
            format = "geojson",
            limit = 10,
            minMag = 5.0,
            orderBy = "time",
            dedupe = 1,
            lat = latitude,
            lon = longitude
        )
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    val earthquakeResponse = Gson().fromJson(response.body(), EarthquakeResponse::class.java)
                    callback(Result.success(earthquakeResponse))
                } else {
                    callback(Result.failure(Throwable("Failed to get earthquakes")))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback(Result.failure(t))
            }
        })
    }
}
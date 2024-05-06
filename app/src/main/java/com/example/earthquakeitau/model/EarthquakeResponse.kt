package com.example.earthquakeitau.model

import com.google.gson.annotations.SerializedName

data class EarthquakeResponse(
    val type: String,
    val features: List<Feature>
)

data class Feature(
    val type: String,
    val properties: Properties,
    val geometry: Geometry
)

data class Properties(
    @SerializedName("mag")
    val magnitude: Double,
    @SerializedName("place")
    val location: String,
    @SerializedName("time")
    val timestamp: Long,
    @SerializedName("updated")
    val updated: Long,
    @SerializedName("tz")
    val timezone: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("felt")
    val felt: Int,
    @SerializedName("cdi")
    val cdi: Double,
    @SerializedName("mmi")
    val mmi: Double,
    @SerializedName("alert")
    val alert: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tsunami")
    val tsunami: Int,
    @SerializedName("sig")
    val sig: Int,
    @SerializedName("net")
    val network: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("ids")
    val ids: String,
    @SerializedName("sources")
    val sources: String,
    @SerializedName("types")
    val types: String,
    @SerializedName("nst")
    val nst: Int,
    @SerializedName("dmin")
    val dmin: String,
    @SerializedName("rms")
    val rms: Double,
    @SerializedName("gap")
    val gap: Double,
    @SerializedName("magType")
    val magType: String,
    @SerializedName("type")
    val type: String
)

data class Geometry(
    val type: String,
    @SerializedName("coordinates")
    val coordinates: List<Double>
)
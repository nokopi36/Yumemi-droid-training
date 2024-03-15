package jp.co.yumemi.droidtraining.data

import kotlinx.serialization.Serializable

@Serializable
data class WeatherRequest(
    val area: String,
    val date: String,
)

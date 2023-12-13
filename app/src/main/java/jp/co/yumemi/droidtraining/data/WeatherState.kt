package jp.co.yumemi.droidtraining.data

import jp.co.yumemi.droidtraining.R

data class WeatherState(
    val weather: Int,
    val minTemperature: String,
    val maxTemperature: String,
    val showErrorDialog: Boolean,
) {
    companion object{
        val weatherMap = mapOf(
            "sunny" to R.drawable.sunny,
            "cloudy" to R.drawable.cloudy,
            "rainy" to R.drawable.rainy,
            "snow" to R.drawable.snow
        )
    }
}

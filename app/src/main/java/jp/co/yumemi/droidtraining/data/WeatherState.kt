package jp.co.yumemi.droidtraining.data

import android.os.Parcelable
import jp.co.yumemi.droidtraining.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherState(
    val weather: Int,
    val minTemperature: String,
    val maxTemperature: String,
    val showErrorDialog: Boolean,
) : Parcelable {
    companion object {
        val weatherMap = mapOf(
            "sunny" to R.drawable.sunny,
            "cloudy" to R.drawable.cloudy,
            "rainy" to R.drawable.rainy,
            "snow" to R.drawable.snow
        )
    }
}

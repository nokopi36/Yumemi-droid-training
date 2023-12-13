package jp.co.yumemi.droidtraining

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.data.WeatherState

class WeatherAppViewModel(private val yumemiWeather: YumemiWeather) : ViewModel() {

    private val _weatherState: MutableLiveData<WeatherState> = MutableLiveData<WeatherState>(
        WeatherState(
            R.drawable.sunny,
            "10",
            "20",
            false
        )
    )
    val weatherState: LiveData<WeatherState> = _weatherState

    private val weatherMap = mapOf(
        "sunny" to R.drawable.sunny,
        "cloudy" to R.drawable.cloudy,
        "rainy" to R.drawable.rainy,
        "snow" to R.drawable.snow
    )

    fun onReloadButtonClicked() {
        _weatherState.value = try {
            val weather = yumemiWeather.fetchThrowsWeather()
            _weatherState.value?.copy(
                weather = weatherMap.getOrDefault(
                    weather,
                    R.drawable.dummy
                ),
                minTemperature = (-5..10).random().toString(),
                maxTemperature = (20..30).random().toString(),
                showErrorDialog = false
            )
        } catch (e: UnknownException) {
            _weatherState.value?.copy(showErrorDialog = true)
        }
    }

    fun onCloseButtonClicked() {
        _weatherState.value = _weatherState.value?.copy(showErrorDialog = false)
    }
}

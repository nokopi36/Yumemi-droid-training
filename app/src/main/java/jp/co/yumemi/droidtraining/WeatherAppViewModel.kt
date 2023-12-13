package jp.co.yumemi.droidtraining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.data.WeatherState
import jp.co.yumemi.droidtraining.data.WeatherState.Companion.weatherMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherAppViewModel(private val yumemiWeather: YumemiWeather) : ViewModel() {

    private val _weatherState: MutableStateFlow<WeatherState> = MutableStateFlow(
        WeatherState(
            R.drawable.sunny,
            "10",
            "20",
            false
        )
    )
    val weatherState: StateFlow<WeatherState> = _weatherState

    val onReloadButtonClicked: () -> Unit = {
        viewModelScope.launch {
            _weatherState.value = try {
                val weather = yumemiWeather.fetchThrowsWeather()
                _weatherState.value.copy(
                    weather = weatherMap.getOrDefault(
                        weather,
                        R.drawable.dummy
                    ),
                    minTemperature = (-5..10).random().toString(),
                    maxTemperature = (20..30).random().toString(),
                    showErrorDialog = false
                )
            } catch (e: UnknownException) {
                _weatherState.value.copy(showErrorDialog = true)
            }
        }
    }

    fun onCloseButtonClicked() {
        _weatherState.value = _weatherState.value.copy(showErrorDialog = false)
    }
}

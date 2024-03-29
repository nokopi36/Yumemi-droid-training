package jp.co.yumemi.droidtraining

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.data.WeatherRequest
import jp.co.yumemi.droidtraining.data.WeatherResponse
import jp.co.yumemi.droidtraining.data.WeatherState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class WeatherAppViewModel @Inject constructor(
    private val yumemiWeather: YumemiWeather,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val weatherStateKey = "weatherState"

    private val _weatherState: MutableStateFlow<WeatherState> = MutableStateFlow(
        savedStateHandle[weatherStateKey] ?: WeatherState(
            R.drawable.sunny,
            "10",
            "20",
            showErrorDialog = false,
            showProgressIndicator = false,
            date = "2000-03-06T12:00",
            area = "ゆめみ",
        )
    )

    val weatherState: StateFlow<WeatherState> = _weatherState

    init {
        viewModelScope.launch {
            weatherState.collect { weatherState ->
                savedStateHandle[weatherStateKey] = weatherState
            }
        }
    }

    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            if (throwable is UnknownException) {
                _weatherState.update { it.copy(showErrorDialog = true) }
            }
        }

    fun onReloadButtonClicked() {
        viewModelScope.launch(exceptionHandler) {
            _weatherState.update { it.copy(showErrorDialog = false, showProgressIndicator = true) }
            val json = WeatherRequest(listOf("東京", "大阪", "広島").random(), "2020-04-01T12:00")
            val weatherResponse = withContext(Dispatchers.IO) {
                yumemiWeather.fetchJsonWeatherAsync(
                    Json.encodeToString(json)
                )
            }
            val weatherData = Json.decodeFromString<WeatherResponse>(weatherResponse)
            _weatherState.update {
                it.copy(
                    weather = WeatherState.weatherMap.getOrDefault(
                        weatherData.weather,
                        R.drawable.dummy
                    ),
                    minTemperature = weatherData.minTemp.toString(),
                    maxTemperature = weatherData.maxTemp.toString(),
                    showErrorDialog = false,
                    date = weatherData.date,
                    area = weatherData.area,
                )
            }
        }.invokeOnCompletion {
            _weatherState.update { it.copy(showProgressIndicator = false) }
        }
    }

    fun onCloseButtonClicked() {
        _weatherState.value = _weatherState.value.copy(showErrorDialog = false)
    }
}

package jp.co.yumemi.droidtraining

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.data.WeatherState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            showProgressIndicator = false
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
            val weather = withContext(Dispatchers.IO) { yumemiWeather.fetchWeatherAsync() }
            _weatherState.update {
                it.copy(
                    weather = WeatherState.weatherMap.getOrDefault(
                        weather,
                        R.drawable.dummy
                    ),
                    minTemperature = (-5..10).random().toString(),
                    maxTemperature = (20..30).random().toString(),
                    showErrorDialog = false
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

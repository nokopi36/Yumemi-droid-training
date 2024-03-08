package jp.co.yumemi.droidtraining

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.data.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
            false
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

    val onReloadButtonClicked: () -> Unit = {
        _weatherState.value = try {
            val weather = yumemiWeather.fetchThrowsWeather()
            _weatherState.value.copy(
                weather = WeatherState.weatherMap.getOrDefault(
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

    fun onCloseButtonClicked() {
        _weatherState.value = _weatherState.value.copy(showErrorDialog = false)
    }
}

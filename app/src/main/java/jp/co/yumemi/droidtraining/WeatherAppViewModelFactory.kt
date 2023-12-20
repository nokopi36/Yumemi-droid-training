package jp.co.yumemi.droidtraining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.co.yumemi.api.YumemiWeather

class WeatherAppViewModelFactory(private val yumemiWeather: YumemiWeather) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherAppViewModel::class.java)) {
            return WeatherAppViewModel(yumemiWeather = yumemiWeather) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

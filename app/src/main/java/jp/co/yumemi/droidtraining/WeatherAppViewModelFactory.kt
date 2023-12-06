package jp.co.yumemi.droidtraining

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherAppViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherAppViewModel::class.java)) {
            return WeatherAppViewModel(context = context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.component.WeatherApp

class MainActivity : AppCompatActivity() {
    private val yumemiWeather = YumemiWeather(applicationContext)
    private val weatherAppViewModel by viewModels<WeatherAppViewModel> {
        WeatherAppViewModelFactory(yumemiWeather)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp(weatherAppViewModel)
        }
    }
}


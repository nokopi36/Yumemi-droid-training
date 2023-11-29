package jp.co.yumemi.droidtraining.component

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.R
import jp.co.yumemi.droidtraining.data.WeatherState

@Composable
fun WeatherApp() {
    val yumemiWeather = YumemiWeather(context = LocalContext.current)
    var weatherState by remember {
        mutableStateOf(
            WeatherState(
                R.drawable.sunny,
                "10",
                "20"
            )
        )
    }
    val weatherMap = mapOf(
        "sunny" to R.drawable.sunny,
        "cloudy" to R.drawable.cloudy,
        "rainy" to R.drawable.rainy,
        "snow" to R.drawable.snow
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val imageSize = maxWidth / 2
        ConstraintLayout {
            val (weatherInfo, actionButtons) = createRefs()
            WeatherInfo(
                weatherIcon = weatherState.weather,
                minTemperature = weatherState.minTemperature,
                maxTemperature = weatherState.maxTemperature,
                iconSize = imageSize,
                modifier = Modifier.constrainAs(weatherInfo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            ActionButtons(
                onReloadClick = {
                    weatherState = WeatherState(
                        weatherMap.getOrDefault
                            (
                            yumemiWeather.fetchThrowsWeather(),
                            R.drawable.dummy
                        ),
                        (-5..10).random().toString(),
                        (20..30).random().toString()
                    )
                },
                onNextClick = { /*TODO*/ },
                modifier = Modifier
                    .constrainAs(actionButtons) {
                        top.linkTo(weatherInfo.bottom, margin = 80.dp)
                        start.linkTo(weatherInfo.start)
                        end.linkTo(weatherInfo.end)
                        width = Dimension.fillToConstraints
                    }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherApp() {
    WeatherApp()
}
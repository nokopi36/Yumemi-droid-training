package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import jp.co.yumemi.droidtraining.component.ActionButtons
import jp.co.yumemi.droidtraining.component.WeatherInfo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp() {
    val yumemiWeather = YumemiWeather(context = LocalContext.current)
    var weatherIcon by remember {
        mutableIntStateOf(R.drawable.dummy)
    }
    var minTemperature by remember {
        mutableStateOf("10")
    }
    var maxTemperature by remember {
        mutableStateOf("20")
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
                weatherIcon = weatherIcon,
                minTemperature = minTemperature,
                maxTemperature = maxTemperature,
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
                    weatherIcon = weatherMap.getOrDefault(
                        yumemiWeather.fetchSimpleWeather(),
                        R.drawable.dummy
                    )
                    minTemperature = (-5..10).random().toString()
                    maxTemperature = (20..30).random().toString()
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

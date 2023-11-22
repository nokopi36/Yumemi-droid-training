package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import jp.co.yumemi.droidtraining.component.ActionButtons
import jp.co.yumemi.droidtraining.component.WeatherInfo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp("10", "20")
        }
    }
}

@Composable
fun WeatherApp(minTemperature: String, maxTemperature: String) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val imageSize = maxWidth / 2
        ConstraintLayout {
            val (weatherInfo, actionButtons) = createRefs()
            WeatherInfo(
                weatherIcon = R.drawable.dummy,
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
                onReloadClick = { /*TODO*/ },
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
    WeatherApp("10", "20")
}

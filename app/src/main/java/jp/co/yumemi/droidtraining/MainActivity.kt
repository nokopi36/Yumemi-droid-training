package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

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

@Composable
fun WeatherInfo(
    weatherIcon: Int,
    minTemperature: String,
    maxTemperature: String,
    iconSize: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.width(iconSize)
    ) {
        Image(
            painter = painterResource(id = weatherIcon),
            contentDescription = "weatherIcon",
            modifier = Modifier
                .size(iconSize)
        )
        Row {
            Text(
                text = stringResource(id = R.string.temperature, minTemperature),
                textAlign = TextAlign.Center,
                color = Color.Blue,
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = stringResource(id = R.string.temperature, maxTemperature),
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun ActionButtons(
    onReloadClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = onReloadClick,
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            shape = RectangleShape,
            modifier = Modifier
                .width(72.dp),
            contentPadding = PaddingValues(
                horizontal = 8.dp,
                vertical = 12.dp
            )
        ) {
            Text(
                text = stringResource(id = R.string.reload),
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }

        Button(
            onClick = onNextClick,
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            ),
            shape = RectangleShape,
            modifier = Modifier
                .width(72.dp),
            contentPadding = PaddingValues(
                horizontal = 8.dp,
                vertical = 12.dp
            )
        ) {
            Text(
                text = stringResource(id = R.string.next),
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherApp() {
    WeatherApp("10", "20")
}

@Preview
@Composable
private fun PreviewWeatherInfo() {
    BoxWithConstraints(
        Modifier.fillMaxSize()
    ) {
        val imageSize = maxWidth / 2
        WeatherInfo(
            weatherIcon = R.drawable.dummy,
            minTemperature = "10",
            maxTemperature = "20",
            iconSize = imageSize
        )
    }
}

@Preview
@Composable
private fun PreviewActionButtons() {
    BoxWithConstraints(
        Modifier.fillMaxSize()
    ) {
        ActionButtons(
            onReloadClick = {},
            onNextClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

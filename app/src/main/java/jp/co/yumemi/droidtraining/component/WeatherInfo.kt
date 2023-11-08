package jp.co.yumemi.droidtraining.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import jp.co.yumemi.droidtraining.R

@Composable
fun WeatherInfo(
    weatherIcon: Int,
    minTemperature: String,
    maxTemperature: String,
    iconSize: Dp,
    modifier: Modifier = Modifier
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

package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp("10°C", "20°C")
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
        val textSize = imageSize / 2
        Column {
            Image(
                painter = painterResource(id = R.drawable.dummy),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(imageSize)
                    .background(Color.Yellow)
            )
            Row {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = minTemperature,
                        modifier = Modifier
                            .width(textSize),
                        textAlign = TextAlign.Center,
                        color = Color.Blue
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.White,
                            containerColor = Color.Black
                        ),
                        shape = RectangleShape,
                        modifier = Modifier
                            .width(72.dp)
                            .padding(top = 80.dp),
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
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = maxTemperature,
                        modifier = Modifier
                            .width(textSize),
                        textAlign = TextAlign.Center,
                        color = Color.Red
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.White,
                            containerColor = Color.Black
                        ),
                        shape = RectangleShape,
                        modifier = Modifier
                            .width(72.dp)
                            .padding(top = 80.dp),
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
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherApp() {
    WeatherApp("10°C", "20°C")
}

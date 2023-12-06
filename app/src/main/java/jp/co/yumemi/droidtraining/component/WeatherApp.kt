package jp.co.yumemi.droidtraining.component

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.co.yumemi.droidtraining.WeatherAppViewModel

@Composable
fun WeatherApp(
    weatherAppViewModel: WeatherAppViewModel = viewModel(),
) {
    val weatherState = weatherAppViewModel.weatherState.observeAsState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val imageSize = maxWidth / 2
        ConstraintLayout {
            val (weatherInfo, actionButtons) = createRefs()
            weatherState.value?.let {
                WeatherInfo(
                    weatherIcon = it.weather,
                    minTemperature = it.minTemperature,
                    maxTemperature = it.maxTemperature,
                    iconSize = imageSize,
                    modifier = Modifier.constrainAs(weatherInfo) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
            ActionButtons(
                onReloadClick = weatherAppViewModel.onReloadButtonClicked,
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

    weatherState.value?.let {
        WeatherErrorDialog(
            showErrorDialog = it.showErrorDialog,
            onReloadClicked = weatherAppViewModel.onReloadButtonClicked,
            onCloseClicked = weatherAppViewModel.onCloseButtonClicked
        )
    }
}

@Preview
@Composable
private fun PreviewWeatherApp() {
    WeatherApp()
}

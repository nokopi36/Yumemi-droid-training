package jp.co.yumemi.droidtraining.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jp.co.yumemi.droidtraining.R

@Composable
fun WeatherErrorDialog(
    showErrorDialog: Boolean,
    onReloadClicked: () -> Unit,
    onCloseClicked: () -> Unit,
) {
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { /*ダイアログ外タップ、戻るボタン操作時に閉じないようにするため意図的に空*/ },
            title = {
                Text(text = stringResource(id = R.string.error))
            },
            text = {
                Text(text = stringResource(id = R.string.error_text))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onReloadClicked()
                    }
                ) {
                    Text(text = stringResource(id = R.string.reload))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onCloseClicked()
                    }
                ) {
                    Text(text = stringResource(id = R.string.close))
                }
            },
        )
    }
}

@Preview
@Composable
private fun PreviewWeatherErrorDialog() {
    WeatherErrorDialog(true, onReloadClicked = {}, onCloseClicked = {})
}

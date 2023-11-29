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
    confirmButton: () -> Unit,
    dismissButton: () -> Unit,
) {
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = {
                Text(text = stringResource(id = R.string.error))
            },
            text = {
                Text(text = stringResource(id = R.string.error_text))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirmButton()
                    }
                ) {
                    Text(text = stringResource(id = R.string.reload))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dismissButton()
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
    WeatherErrorDialog(true, confirmButton = {}, dismissButton = {})
}

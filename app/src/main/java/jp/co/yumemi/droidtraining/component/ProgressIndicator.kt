package jp.co.yumemi.droidtraining.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProgressIndicator(
    showProgressIndicator: Boolean,
) {
    if (showProgressIndicator) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0x80000000)),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun PreviewProgressIndicator() {
    ProgressIndicator(showProgressIndicator = true)
}

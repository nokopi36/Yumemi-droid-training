package jp.co.yumemi.droidtraining.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.droidtraining.R

@Composable
fun ActionButtons(
    onReloadClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
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

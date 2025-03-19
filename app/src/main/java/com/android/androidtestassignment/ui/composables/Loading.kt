package com.android.androidtestassignment.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Preview
@Composable
fun LoadingDialog(
    spinnerSize: Dp = 40.dp,
    spinnerColor: Color = MaterialTheme.colorScheme.primary
) {
    Dialog(onDismissRequest = {}) {

        CircularProgressIndicator(
            color = spinnerColor,
            strokeWidth = 2.dp,
            modifier = Modifier
                .size(spinnerSize)
        )
    }
}

@Composable
fun Loading(
    spinnerSize: Dp = 24.dp,
    modifier: Modifier = Modifier,
    spinnerColor: Color = MaterialTheme.colorScheme.primary
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            color = spinnerColor,
            strokeWidth = 2.dp,
            modifier = Modifier
                .size(spinnerSize)
                .align(Alignment.Center)
        )
    }
}
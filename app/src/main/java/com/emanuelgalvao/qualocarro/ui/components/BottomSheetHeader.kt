package com.emanuelgalvao.qualocarro.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emanuelgalvao.qualocarro.R

@Composable
fun BottomSheetHeader(
    title: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title.uppercase(),
            style = TextStyle(
                color = colorResource(id = R.color.text_dark),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        IconButton(
            onClick = {
                onDismiss()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun BottomSheetHeaderPreview() {
    MaterialTheme {
        BottomSheetHeader(
            title = "Title",
            onDismiss = {}
        )
    }
}
package com.emanuelgalvao.qualocarro.ui.dialog

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.ui.components.BottomSheetHeader
import com.emanuelgalvao.qualocarro.ui.components.InfoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoBottomSheet(
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        modifier = Modifier
            .fillMaxHeight(0.6F)
    ) {
        BottomSheetHeader(
            title = stringResource(id = R.string.about),
            onDismiss = { onDismiss() }
        )
        Text(
            text = stringResource(id = R.string.developer),
            style = TextStyle(
                fontSize = 16.sp,
                color = colorResource(id = R.color.text_dark),
            ),
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        )
        InfoItem(
            icon = painterResource(id = R.drawable.ic_email),
            text = stringResource(id = R.string.developer_email)
        )
        InfoItem(
            icon = painterResource(id = R.drawable.ic_github),
            text = stringResource(id = R.string.developer_github)
        )
        InfoItem(
            icon = painterResource(id = R.drawable.ic_linkedin),
            text = stringResource(id = R.string.developer_linkedin)
        )
    }
}

@Preview
@Composable
fun InfoBottomSheetPreview() {
    MaterialTheme {
        InfoBottomSheet(
            onDismiss = {}
        )
    }
}
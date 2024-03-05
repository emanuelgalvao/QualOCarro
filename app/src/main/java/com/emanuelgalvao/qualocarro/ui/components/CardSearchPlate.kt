package com.emanuelgalvao.qualocarro.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emanuelgalvao.qualocarro.R

@Composable
fun CardSearchPlate(
    onSearch: (String) -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val plateInputValue = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        shape = RoundedCornerShape(
            topStart = 40.dp
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 32.dp,
                    top = 40.dp,
                    end = 32.dp
                )
        ) {
            Text(
                text = stringResource(id = R.string.type_the_board),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            PlateTextField(
                value = plateInputValue.value,
                onValueChange = { plateInputValue.value = it },
                modifier = Modifier.padding(top = 10.dp)
            )
            LoadingButton(
                title = stringResource(id = R.string.search).uppercase(),
                isLoading = isLoading,
                onClick = {
                    keyboardController?.hide()
                    onSearch(plateInputValue.value)
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
        }
    }
}

@Preview
@Composable
fun CardSearchPlatePreview() {
    MaterialTheme {
        CardSearchPlate(
            isLoading = false,
            onSearch = {}
        )
    }
}
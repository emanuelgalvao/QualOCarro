package com.emanuelgalvao.qualocarro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.util.PlateMaskTransformation

@Composable
fun PlateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                stringResource(id = R.string.placeholder_board),
                style = TextStyle(
                    color = colorResource(id = R.color.text_gray),
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Characters,
            keyboardType = KeyboardType.Text
        ),
        textStyle = TextStyle(
            fontSize = 60.sp,
            color = colorResource(id = R.color.text_dark),
            textAlign = TextAlign.Center
        ),
        maxLines = 1,
        singleLine = true,
        visualTransformation = PlateMaskTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.background_input),
                shape = RoundedCornerShape(10.dp)
            )
    )
}

@Preview
@Composable
fun PlateTextFieldPreview() {
    MaterialTheme {
        PlateTextField(
            value = "",
            onValueChange = {}
        )
    }
}
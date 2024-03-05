package com.emanuelgalvao.qualocarro.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emanuelgalvao.qualocarro.R

@Composable
fun HomeHeader(
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(200.dp)
                .padding(
                    top = 16.dp,
                    start = 16.dp
                )
        )
        Image(
            painter = painterResource(id = R.drawable.car),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.BottomEnd)
                .offset(
                    x = 72.dp,
                    y = 16.dp
                )
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp),
            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White),
            onClick = { onInfoClick() }
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    MaterialTheme {
        HomeHeader(
            onInfoClick = { },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
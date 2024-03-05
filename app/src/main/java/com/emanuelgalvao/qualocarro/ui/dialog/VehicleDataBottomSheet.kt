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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.model.Vehicle
import com.emanuelgalvao.qualocarro.ui.components.BottomSheetHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDataBottomSheet(
    data: Vehicle,
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
            title = stringResource(id = R.string.vehicle_data),
            onDismiss = { onDismiss() }
        )
        VehicleDataItem(
            text = stringResource(id = R.string.brand, data.marca),
            modifier = Modifier
                .padding(top = 16.dp)
        )
        VehicleDataItem(
            text = stringResource(id = R.string.year, "${data.ano}/${data.anoModelo}")
        )
        VehicleDataItem(text = stringResource(id = R.string.color, data.cor))
        VehicleDataItem(text = stringResource(id = R.string.chassi, data.chassi))
        VehicleDataItem(text = stringResource(id = R.string.board, data.placa))
        VehicleDataItem(
            text = stringResource(
                id = R.string.city_state,
                "${data.municipio}/${data.uf}"
            )
        )
        VehicleDataItem(text = stringResource(id = R.string.situation, data.situacao))
    }
}

@Composable
fun VehicleDataItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TextStyle(
            color = colorResource(R.color.text_dark),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        ),
        modifier = modifier
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp)
    )
}

@Preview
@Composable
fun VehicleDataBottomSheetPreview() {
    MaterialTheme {
        VehicleDataBottomSheet(
            data = Vehicle(
                ano = "2023",
                anoModelo = "2024",
                chassi = "chassi",
                cor = "Branco",
                marca = "Fiat",
                municipio = "Curitiba",
                placa = "AAA-1234",
                situacao = "Normal",
                uf = "PR"
            ),
            onDismiss = {}
        )
    }
}
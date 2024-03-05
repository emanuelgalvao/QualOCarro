package com.emanuelgalvao.qualocarro.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.emanuelgalvao.qualocarro.R
import com.emanuelgalvao.qualocarro.ui.components.CardSearchPlate
import com.emanuelgalvao.qualocarro.ui.components.HomeHeader
import com.emanuelgalvao.qualocarro.ui.dialog.InfoBottomSheet
import com.emanuelgalvao.qualocarro.ui.dialog.VehicleDataBottomSheet
import com.emanuelgalvao.qualocarro.viewmodel.MainViewState
import com.emanuelgalvao.qualocarro.viewmodel.MainViewIntent
import com.emanuelgalvao.qualocarro.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainView(modifier: Modifier = Modifier) {

    val viewModel: MainViewModel = koinViewModel()

    val state = viewModel.state.observeAsState().value

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = colorResource(id = R.color.background_snackbar)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(id = R.color.background_primary))
        ) {
            HomeHeader(
                onInfoClick = {
                    viewModel.handleIntent(MainViewIntent.OpenInfoBottomSheet)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(2F)
            )
            CardSearchPlate(
                isLoading = state is MainViewState.Loading,
                onSearch = { plate ->
                    viewModel.handleIntent(MainViewIntent.SearchVehicle(plate))
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        when (state) {
            is MainViewState.Success -> {
                VehicleDataBottomSheet(
                    data = state.data,
                    onDismiss = {
                        viewModel.handleIntent(MainViewIntent.CloseBottomSheet)
                    }
                )
            }

            is MainViewState.OpenInfo -> {
                InfoBottomSheet(
                    onDismiss = {
                        viewModel.handleIntent(MainViewIntent.CloseBottomSheet)
                    }
                )
            }

            is MainViewState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(state.message)
                }
            }

            else -> return@Scaffold
        }
    }
}

@Preview
@Composable
fun MainViewPreview() {
    MaterialTheme {
        MainView()
    }
}
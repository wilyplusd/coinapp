package ru.netology.coinapp.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.netology.coinapp.viewmodel.AssetViewModel

@Composable
fun CoinApp() {
    val viewModel: AssetViewModel = hiltViewModel<AssetViewModel>()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AssetList.name
    ) {
        composable(Screen.AssetList.name) {
            AssetListScreen(
                viewModel,
                onAssetDetails = {
                    viewModel.currentAsset = it
                    navController.navigate(Screen.AssetDetails.name)
                }
            )
        }
        composable(Screen.AssetDetails.name) {
            AssetDetailsScreen(
                viewModel
            )
        }
    }
}
package ru.netology.coinapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.netology.coinapp.R
import ru.netology.coinapp.viewmodel.AssetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinApp() {
    val assetViewModel = hiltViewModel<AssetViewModel>()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    @Composable
    fun isAtMainScreen(): Boolean {
        return navBackStackEntry?.destination?.route == Screen.AssetList.name
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                navigationIcon = {
                    if (!isAtMainScreen()) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.icon_back)
                            )
                        }
                    }
                },
                actions = {
                    if (isAtMainScreen()) {
                        IconButton(onClick = { assetViewModel.loadAssets() }) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = stringResource(R.string.icon_refresh)
                            )
                        }
                    }
                    IconButton(onClick = { navController.navigate(Screen.Settings.name) }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(R.string.icon_settings)
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.AssetList.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Settings.name) {
                SettingsScreen()
            }
            composable(Screen.AssetList.name) {
                AssetListScreen(
                    viewModel = assetViewModel,
                    onAssetDetails = { navController.navigate(Screen.AssetDetails.name) }
                )
            }
            composable(Screen.AssetDetails.name) {
                AssetDetailsScreen(
                    asset = assetViewModel.currentAsset,
                )
            }
        }
    }
}

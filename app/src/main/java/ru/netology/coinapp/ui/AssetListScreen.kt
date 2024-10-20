package ru.netology.coinapp.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.netology.coinapp.R
import ru.netology.coinapp.dto.Asset
import ru.netology.coinapp.viewmodel.AssetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetListScreen(
    onAssetDetails: () -> Unit,
) {
    val viewModel = hiltViewModel<AssetViewModel>()
    val assets by viewModel.assetList.observeAsState(listOf())

    LaunchedEffect(Unit) {
        viewModel.loadAssets()
    }

    LazyColumn {
        items(assets) { item ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                onClick = {
                    viewModel.currentAsset = item
                    onAssetDetails()
                }
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(24.dp)
                )
                Row {
                    Text(stringResource(R.string.asset_supply))
                    Text(item.supply.toString())
                }

            }
        }
    }
}

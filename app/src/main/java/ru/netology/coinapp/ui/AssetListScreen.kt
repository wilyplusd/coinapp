package ru.netology.coinapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.netology.coinapp.dto.Asset
import ru.netology.coinapp.viewmodel.AssetViewModel

@Composable
fun AssetListScreen(
    viewModel: AssetViewModel,
    onAssetDetails: (asset: Asset) -> Unit,
) {
    val assets by viewModel.assetList.observeAsState(listOf())

    LaunchedEffect(Unit) {
        viewModel.loadAssets()
    }

    LazyColumn {
        items(assets!!) { item ->
            Surface(onClick = { onAssetDetails(item) }) {
                Text( item.name, Modifier.padding(24.dp) )
            }
        }
    }
}

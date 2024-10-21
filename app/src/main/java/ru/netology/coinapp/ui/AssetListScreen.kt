package ru.netology.coinapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.netology.coinapp.R
import ru.netology.coinapp.viewmodel.AssetViewModel

@Composable
fun AssetListScreen(
    viewModel: AssetViewModel,
    onAssetDetails: () -> Unit,
) {
    val assets by viewModel.assetList.observeAsState(listOf())
    val loading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState("")

    Column (
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (loading) {
            CircularProgressIndicator (
                modifier = Modifier.width(24.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        if (error.isNotEmpty()) {
            ErrorDialog (
                message = error,
                onDismissRequest = { viewModel.resetError() }
            )
        }

        LazyColumn (
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(assets) { item ->
                ElevatedCard(
                    modifier = Modifier.fillParentMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    onClick = {
                        viewModel.currentAsset = item
                        onAssetDetails()
                    }
                ) {
                    Column (
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text (
                            text = item.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                        Text(
                            text = "${stringResource(R.string.asset_price_usd)}: ${String.format("%.2f", item.priceUsd)}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }
        }
    }
}

package ru.netology.coinapp.ui

import android.util.Log
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
fun AssetDetailsScreen(
    viewModel: AssetViewModel,
) {
    val asset = viewModel.currentAsset

    Text(asset.name + " - " + asset.id)
}

package ru.netology.coinapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.netology.coinapp.viewmodel.AssetViewModel

@Composable
fun AssetListScreen(viewModel: AssetViewModel) {
    val assets by viewModel.assets.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAssets()
    }

    LazyColumn {
        items(assets!!) { item ->
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

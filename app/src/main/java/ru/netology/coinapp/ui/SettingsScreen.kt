package ru.netology.coinapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.netology.coinapp.R
import ru.netology.coinapp.repository.SettingsRepository.Theme
import ru.netology.coinapp.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen() {
    val viewModel = hiltViewModel<SettingsViewModel>()

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(stringResource(R.string.theme))
        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.setTheme(Theme.SYSTEM) }
            ) {
                Text(stringResource(R.string.theme_system))
            }
            Button(onClick = { viewModel.setTheme(Theme.LIGHT) }) {
                Text(stringResource(R.string.theme_light))
            }
            Button(onClick = { viewModel.setTheme(Theme.DARK) }) {
                Text(stringResource(R.string.theme_dark))
            }
        }
    }

}
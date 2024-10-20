package ru.netology.coinapp.repository.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.netology.coinapp.repository.SettingsRepository
import ru.netology.coinapp.repository.SettingsRepository.Theme
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    private companion object {
        val THEME = stringPreferencesKey("theme")
    }

    override val theme:  Flow<Theme> = dataStore.data.map { preferences ->
        val theme= preferences[THEME]
        when (theme) {
            Theme.LIGHT.name -> Theme.LIGHT
            Theme.DARK.name -> Theme.DARK
            else -> Theme.SYSTEM
        }
    }

    override suspend fun setTheme(theme: Theme) {
        dataStore.edit { preferences ->
            preferences[THEME] = theme.name
        }
    }
}
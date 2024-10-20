package ru.netology.coinapp.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    enum class Theme {
        SYSTEM, LIGHT, DARK
    }
    val theme: Flow<Theme>
    suspend fun setTheme(theme: Theme)
}
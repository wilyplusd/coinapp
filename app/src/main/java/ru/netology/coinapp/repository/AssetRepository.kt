package ru.netology.coinapp.repository

import kotlinx.coroutines.flow.Flow
import ru.netology.coinapp.dto.Asset

interface AssetRepository {
    val data: Flow<List<Asset>>
    suspend fun load()
}

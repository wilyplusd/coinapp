package ru.netology.coinapp.repository.impl

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import ru.netology.coinapp.repository.AssetRepository
import ru.netology.coinapp.api.*
import ru.netology.coinapp.dao.AssetDao
import ru.netology.coinapp.dto.*
import ru.netology.coinapp.entity.AssetEntity
import ru.netology.coinapp.entity.toDto
import ru.netology.coinapp.entity.toEntity
import ru.netology.coinapp.error.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssetRepositoryImpl @Inject constructor(
    private val assetDao: AssetDao,
    private val apiService: ApiService,
) : AssetRepository {
    override val data = assetDao.getAll()
        .map(List<AssetEntity>::toDto)
        .flowOn(Dispatchers.Default)

    override suspend fun load() {
        val response = apiService.getAssets("")
        if (!response.isSuccessful) {
            throw ApiError(response.code(), response.message())
        }
        val body = response.body() ?: throw ApiError(response.code(), response.message())
        assetDao.insert(body.data.toEntity())
    }
}

package ru.netology.coinapp.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.netology.coinapp.entity.AssetEntity

@Dao
interface AssetDao {
    @Query("SELECT * FROM AssetEntity")
    fun getAll(): Flow<List<AssetEntity>>

    @Query("SELECT * FROM AssetEntity WHERE id = :id")
    fun getById(id: String): AssetEntity

    @Query("SELECT COUNT(*) FROM AssetEntity")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(assets: List<AssetEntity>)
}
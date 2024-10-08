package ru.netology.coinapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.netology.coinapp.dao.AssetDao
import ru.netology.coinapp.entity.AssetEntity

@Database(entities = [AssetEntity::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun assetDao(): AssetDao
}
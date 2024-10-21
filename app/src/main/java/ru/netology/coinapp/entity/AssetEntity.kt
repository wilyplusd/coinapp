package ru.netology.coinapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.coinapp.dto.Asset

@Entity
data class AssetEntity(
    @PrimaryKey
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val supply: Double,
    val priceUsd: Double,
    val marketCapUsd: Double,
    val volumeUsd24Hr: Double,
    val changePercent24Hr: Double,
    val explorer: String?,
) {
    fun toDto() = Asset(id, rank, symbol, name, supply, priceUsd, marketCapUsd, volumeUsd24Hr, changePercent24Hr, explorer)

    companion object {
        fun fromDto(dto: Asset) =
            AssetEntity(dto.id, dto.rank, dto.symbol, dto.name, dto.supply, dto.priceUsd, dto.marketCapUsd, dto.volumeUsd24Hr, dto.changePercent24Hr, dto.explorer)
    }
}

fun List<AssetEntity>.toDto(): List<Asset> = map(AssetEntity::toDto)
fun List<Asset>.toEntity(): List<AssetEntity> = map(AssetEntity::fromDto)

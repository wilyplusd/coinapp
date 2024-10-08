package ru.netology.coinapp.dto

data class Asset(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val supply: Double,
    val priceUsd: Double,
    val marketCapUsd: Double,
    val volumeUsd24Hr: Double,
    val changePercent24Hr: Double,
)

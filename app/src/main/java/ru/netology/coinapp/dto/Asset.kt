package ru.netology.coinapp.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
    val explorer: String?,
) : Parcelable
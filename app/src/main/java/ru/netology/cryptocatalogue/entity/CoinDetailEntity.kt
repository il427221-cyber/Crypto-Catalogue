package ru.netology.cryptocatalogue.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail


@Entity(tableName = "coinDetails")
data class CoinDetailEntity (
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String,
    val rank: Int,
    val price_usd: String?,
    val percent_change_24h: String?,
    val percent_change_1h: String?,
    val percent_change_7d: String?,
    val volume24: Double?
) {

    fun toDto() = CryptoCoinDetail(
        id = id,
        symbol = symbol,
        name = name,
        rank = rank,
        price_usd = price_usd,
        percent_change_24h = percent_change_24h,
        percent_change_1h = percent_change_1h,
        percent_change_7d = percent_change_7d,
        volume24 = volume24
    )

    companion object {
        fun fromDto(dto: CryptoCoinDetail) = CoinDetailEntity(
            id = dto.id,
            symbol = dto.symbol,
            name = dto.name,
            rank = dto.rank,
            price_usd = dto.price_usd,
            percent_change_24h = dto.percent_change_24h,
            percent_change_1h = dto.percent_change_1h,
            percent_change_7d = dto.percent_change_7d,
            volume24 = dto.volume24
        )
    }
}

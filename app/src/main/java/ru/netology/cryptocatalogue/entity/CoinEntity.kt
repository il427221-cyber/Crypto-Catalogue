package ru.netology.cryptocatalogue.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.cryptocatalogue.dto.CryptoCoin

@Entity(tableName = "coinBase")
data class CoinEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val symbol: String,
    val name: String,
    val price_usd: String?,
    val percent_change_24h: String?
) {

    fun toDto() = CryptoCoin(
        id = id.toString(),
        symbol = symbol,
        name = name,
        price_usd = price_usd,
        percent_change_24h = percent_change_24h
    )

    companion object {
        fun fromDto(dto: CryptoCoin) = CoinEntity(
            id = dto.id.toInt(),
            symbol = dto.symbol,
            name = dto.name,
            price_usd = dto.price_usd,
            percent_change_24h = dto.percent_change_24h
        )
    }

    fun List<CoinEntity>.toDto(): List<CryptoCoin> = map(CoinEntity::toDto)
    fun List<CryptoCoin>.toEntity(): List<CoinEntity> = map(CoinEntity::fromDto)



}


package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.NewGodownDto
import me.darthwithap.api.models.entities.dto.StockDto
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.utils.extensions.toIsoFormat
import java.util.*

object StockDtoMapper : DomainMapper<StockDto, Stock> {
    override fun mapToDomainModel(model: StockDto): Stock {
        return with(model) {
            Stock(
                brand, code, godown, id, name, quantity, range, shop
            )

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: Stock): StockDto {
        return with(domainModel) {
            Date(System.currentTimeMillis()).toIsoFormat()?.let {
                StockDto(
                    brand, code, it, godown, id, name, quantity, range, shop, it, 0
                )
            }!!
        }
    }
}
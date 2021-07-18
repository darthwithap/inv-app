package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.StockDto
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.utils.extensions.toIsoFormat
import java.util.*

object StockDtoListMapper : DomainMapper<List<StockDto>, List<Stock>> {
    override fun mapToDomainModel(model: List<StockDto>): List<Stock> {
        return model.map { stockDto ->
            with(stockDto) {
                Stock(
                    brand, code, godown, id, name, quantity, range, shop
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: List<Stock>): List<StockDto> {
        return domainModel.map { stock ->
            with(stock) {
                Date(System.currentTimeMillis()).toIsoFormat()?.let {
                    StockDto(
                        brand, code, it, godown, id, name, quantity, range, shop, it, 0
                    )
                }!!
            }
        }
    }

}
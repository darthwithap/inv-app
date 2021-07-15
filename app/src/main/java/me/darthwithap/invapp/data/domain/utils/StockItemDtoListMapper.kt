package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.InvoiceDto
import me.darthwithap.api.models.entities.dto.StockDto
import me.darthwithap.api.models.entities.dto.StockHistoryDto
import me.darthwithap.api.models.entities.dto.StockItemDto
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.data.domain.models.StockHistory
import me.darthwithap.invapp.data.domain.models.StockItem
import me.darthwithap.invapp.utils.extensions.toIsoFormat
import java.util.*

object StockItemDtoListMapper : DomainMapper<List<StockItemDto>, List<StockItem>> {
    override fun mapToDomainModel(model: List<StockItemDto>): List<StockItem> {
        return model.map { stockItemDto ->
            with(stockItemDto) {
                StockItem(displayName, godown, id, quantity)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: List<StockItem>): List<StockItemDto> {
        return domainModel.map { stockItem ->
            with(stockItem) {
                StockItemDto(displayName, godown, id, quantity)
            }
        }
    }
}



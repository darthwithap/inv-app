package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.InvoiceDto
import me.darthwithap.api.models.entities.dto.StockDto
import me.darthwithap.api.models.entities.dto.StockHistoryDto
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.data.domain.models.StockHistory
import me.darthwithap.invapp.utils.extensions.toIsoFormat
import java.util.*

object StockHistoryDtoListMapper : DomainMapper<List<StockHistoryDto>, List<StockHistory>> {
    override fun mapToDomainModel(model: List<StockHistoryDto>): List<StockHistory> {
        return model.map { stockHistoryDto ->
            with(stockHistoryDto) {
                StockHistory(
                    id, quantity, stock, StockHistoryUserDtoMapper.mapToDomainModel(user)
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: List<StockHistory>): List<StockHistoryDto> {
        return domainModel.map { stockHistory ->
            with(stockHistory) {
                Date(System.currentTimeMillis()).toIsoFormat()?.let {
                    StockHistoryDto(
                        it,
                        id,
                        quantity,
                        stock,
                        it,
                        StockHistoryUserDtoMapper.mapFromDomainModel(user),
                        0
                    )
                }
            }!!
        }
    }

}
package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.NewGodownDto
import me.darthwithap.api.models.entities.dto.StockDto
import me.darthwithap.api.models.entities.dto.StockHistoryDto
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.data.domain.models.StockHistory
import me.darthwithap.invapp.utils.extensions.toIsoFormat
import java.util.*

object StockHistoryDtoMapper : DomainMapper<StockHistoryDto, StockHistory> {
    override fun mapToDomainModel(model: StockHistoryDto): StockHistory {
        return with(model) {
            StockHistory(
                id, quantity, stock, StockHistoryUserDtoMapper.mapToDomainModel(user)
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: StockHistory): StockHistoryDto {
        return with(domainModel) {
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
            }!!
        }
    }
}
package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.NewGodownDto
import me.darthwithap.api.models.entities.dto.StockDto
import me.darthwithap.api.models.entities.dto.StockHistoryUserDto
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.data.domain.models.StockHistoryUser
import me.darthwithap.invapp.utils.extensions.toIsoFormat
import java.util.*

object StockHistoryUserDtoMapper : DomainMapper<StockHistoryUserDto, StockHistoryUser> {
    override fun mapToDomainModel(model: StockHistoryUserDto): StockHistoryUser {
        return with(model) {
            StockHistoryUser(
                displayName, id
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: StockHistoryUser): StockHistoryUserDto {
        return with(domainModel) {
            StockHistoryUserDto(
                displayName, id
            )
        }
    }
}
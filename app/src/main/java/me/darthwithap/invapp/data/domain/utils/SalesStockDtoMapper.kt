package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.SalesStockDto
import me.darthwithap.invapp.data.domain.models.SalesStock

object SalesStockDtoMapper : DomainMapper<SalesStockDto, SalesStock> {
    override fun mapToDomainModel(model: SalesStockDto): SalesStock {
        return with(model) {
            SalesStock(
                brand, code, id, name, range
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: SalesStock): SalesStockDto {
        return with(domainModel) {
            SalesStockDto(
                brand, code, id, name, range
            )
        }
    }
}
package me.darthwithap.invapp.data.domain.utils

import me.darthwithap.api.models.entities.dto.PendingStockDto
import me.darthwithap.invapp.data.domain.models.PendingStock

object PendingStockDtoMapper : DomainMapper<PendingStockDto, PendingStock> {
    override fun mapToDomainModel(model: PendingStockDto): PendingStock {
        return with(model) {
            PendingStock(
                brand, id, name
            )
        }
    }

    override fun mapFromDomainModel(domainModel: PendingStock): PendingStockDto {
        return with(domainModel) {
            PendingStockDto(
                brand, id, name
            )
        }
    }
}
package me.darthwithap.invapp.data.domain.utils

import me.darthwithap.api.models.entities.dto.ProductDto
import me.darthwithap.invapp.data.domain.models.Product

object ProductDtoListMapper : DomainMapper<List<ProductDto>, List<Product>> {
    override fun mapToDomainModel(model: List<ProductDto>): List<Product> {
        return model.map { productDto ->
            with(productDto) {
                Product(
                    godown,
                    id,
                    quantity,
                    PendingStockDtoMapper.mapToDomainModel(stock),
                    deliveredStatus
                )
            }
        }
    }

    override fun mapFromDomainModel(domainModel: List<Product>): List<ProductDto> {
        return domainModel.map { product ->
            with(product) {
                ProductDto(
                    deliveredStatus,
                    godown,
                    id,
                    qty,
                    PendingStockDtoMapper.mapFromDomainModel(stock)
                )
            }
        }
    }

}
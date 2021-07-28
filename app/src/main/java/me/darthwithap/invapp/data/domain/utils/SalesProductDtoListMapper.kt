package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.SalesProductDto
import me.darthwithap.invapp.data.domain.models.SalesProduct

object SalesProductDtoListMapper : DomainMapper<List<SalesProductDto>, List<SalesProduct>> {
    override fun mapToDomainModel(model: List<SalesProductDto>): List<SalesProduct> {
        return model.map { salesProductDto ->
            with(salesProductDto) {
                SalesProduct(
                    deliveredStatus,
                    godown,
                    id,
                    quantity,
                    SalesStockDtoMapper.mapToDomainModel(stock)
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: List<SalesProduct>): List<SalesProductDto> {
        return domainModel.map { salesProduct ->
            with(salesProduct) {
                SalesProductDto(
                    deliveredStatus,
                    godown,
                    id,
                    quantity,
                    SalesStockDtoMapper.mapFromDomainModel(stock)
                )
            }
        }
    }

}
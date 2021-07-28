package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.ProductDto
import me.darthwithap.api.models.entities.dto.SalesInvoiceDto
import me.darthwithap.invapp.data.domain.models.Product
import me.darthwithap.invapp.data.domain.models.SalesInvoice

object SalesInvoiceDtoListMapper : DomainMapper<List<SalesInvoiceDto>, List<SalesInvoice>> {
    override fun mapToDomainModel(model: List<SalesInvoiceDto>): List<SalesInvoice> {
        return model.map { salesInvoiceDto ->
            with(salesInvoiceDto) {
                SalesInvoice(
                    createdAt,
                    customerName,
                    id,
                    SalesProductDtoListMapper.mapToDomainModel(products),
                    shop,
                    updatedAt,
                    user
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: List<SalesInvoice>): List<SalesInvoiceDto> {
        return domainModel.map { salesInvoice ->
            with(salesInvoice) {
                SalesInvoiceDto(
                    createdAt,
                    customerName,
                    id,
                    SalesProductDtoListMapper.mapFromDomainModel(products),
                    shop,
                    updatedAt,
                    user,
                    0
                )
            }
        }
    }

}
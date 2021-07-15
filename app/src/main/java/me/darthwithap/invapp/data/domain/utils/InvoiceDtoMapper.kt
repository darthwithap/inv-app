package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.InvoiceDto
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.utils.extensions.toIsoFormat
import java.util.*


object InvoiceDtoMapper : DomainMapper<InvoiceDto, Invoice> {
    override fun mapToDomainModel(model: InvoiceDto): Invoice {
        return with(model) {
            return@with Invoice(
                id, user, customerName,
                ProductDtoListMapper.mapToDomainModel(products),
                shop
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: Invoice): InvoiceDto {
        return with(domainModel) {
            Date(System.currentTimeMillis()).toIsoFormat()?.let {
                InvoiceDto(
                    System.currentTimeMillis().toString(), customerName, id,
                    products?.let { ProductDtoListMapper.mapFromDomainModel(it) }!!, shop,
                    it, userName, 0
                )
            }!!
        }
    }
}
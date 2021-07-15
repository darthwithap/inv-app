package me.darthwithap.invapp.data.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import me.darthwithap.api.models.entities.dto.InvoiceDto
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.utils.extensions.toIsoFormat
import java.util.*

object InvoiceDtoListMapper : DomainMapper<List<InvoiceDto>, List<Invoice>> {
    override fun mapToDomainModel(model: List<InvoiceDto>): List<Invoice> {
        return model.map { invoiceDto ->
            with(invoiceDto) {
                Invoice(
                    id,
                    user,
                    customerName,
                    ProductDtoListMapper.mapToDomainModel(products),
                    shop
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun mapFromDomainModel(domainModel: List<Invoice>): List<InvoiceDto> {
        return domainModel.map { invoice ->
            with(invoice) {
                Date(System.currentTimeMillis()).toIsoFormat()?.let {
                    products?.let { it1 -> ProductDtoListMapper.mapFromDomainModel(it1) }
                        ?.let { it2 ->
                            InvoiceDto(
                                it,
                                customerName,
                                id,
                                it2,
                                shop,
                                it,
                                userName,
                                0
                            )
                        }
                }!!
            }
        }
    }

}
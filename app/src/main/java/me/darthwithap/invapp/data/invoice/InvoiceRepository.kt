package me.darthwithap.invapp.data.invoice

import me.darthwithap.api.models.requests.CreateInvoiceRequest
import me.darthwithap.api.models.requests.PendingOrdersUpdateRequest
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.data.domain.utils.InvoiceDtoListMapper
import me.darthwithap.invapp.data.invoice.network.InvoiceDataSource
import me.darthwithap.invapp.utils.Result
import java.lang.Exception

object InvoiceRepository {

    suspend fun getPendingOrdersForGodown(
        godownId: String,
        refresh: Boolean
    ): Result<List<Invoice>> {
        if (refresh) {
            return when (val response =
                InvoiceDataSource.getPendingOrdersForGodown(godownId)
            ) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        Result.Success(InvoiceDtoListMapper.mapToDomainModel(response.data.data.invoices))
                    }
                }
                is Result.Error -> {
                    Result.Error(response.exception)
                }
                else -> Result.Loading
            }
        } else {
            return Result.Error(Exception("Cache Error"))
        }
        // TODO Restore from cache first
        // FROM CACHE
//        else {
//            val mapper = WeatherMapperLocal()
//            val forecast = localDataSource.getWeather()
//            if (forecast != null) {
//                Result.Success(mapper.transformToDomain(forecast))
//            } else {
//                Result.Success()
//            }
//        }
    }

    suspend fun createInvoice(
        createInvoiceRequest: CreateInvoiceRequest
    ): Result<String> {
        return when (val response =
            InvoiceDataSource.createInvoice(createInvoiceRequest)
        ) {
            is Result.Success -> {
                if (response.data.error) {
                    Result.Error(Exception(response.data.message))
                } else {
                    Result.Success(response.data.data.invoiceId)
                }
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
            else -> Result.Loading
        }
    }

    suspend fun updatePendingOrdersStatusFor(
        pendingOrdersUpdateRequest: PendingOrdersUpdateRequest,
    ): Result<String> {
        return when (val response =
            InvoiceDataSource.updatePendingOrdersStatusFor(pendingOrdersUpdateRequest)
        ) {
            is Result.Success -> {
                if (response.data.error) {
                    Result.Error(Exception(response.data.message))
                } else {
                    Result.Success(response.data.message)
                }
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
            else -> Result.Loading
        }
    }

}
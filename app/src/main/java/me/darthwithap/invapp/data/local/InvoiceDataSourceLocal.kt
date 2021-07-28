package me.darthwithap.invapp.data.local

import android.content.Context
import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.requests.CreateInvoiceRequest
import me.darthwithap.api.models.requests.PendingOrdersUpdateRequest
import me.darthwithap.api.models.responses.CreateInvoiceResponse
import me.darthwithap.api.models.responses.PendingOrdersUpdateResponse
import me.darthwithap.api.models.responses.PendingOrdersResponse
import me.darthwithap.invapp.data.local.entity.GodownEntity
import me.darthwithap.invapp.data.local.entity.InvoiceEntity
import me.darthwithap.invapp.utils.Result
import org.json.JSONObject
import java.io.IOException

class InvoiceDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    private val invoiceDao = db.invoiceDao()

    suspend fun insertInvoice(invoice: InvoiceEntity) {
        invoiceDao.insertInvoice(invoice)
    }
}

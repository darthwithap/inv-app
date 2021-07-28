package me.darthwithap.invapp.data.local

import android.content.Context
import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.responses.SalesResponse
import me.darthwithap.invapp.data.local.entity.GodownEntity
import me.darthwithap.invapp.data.local.entity.SalesInvoiceEntity
import me.darthwithap.invapp.utils.Result
import org.json.JSONObject
import java.io.IOException

class SalesDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    private val salesDao = db.salesDao()

    suspend fun insertSales(sale: SalesInvoiceEntity) {
        salesDao.insertSales(sale)
    }

    suspend fun getSales(): List<SalesInvoiceEntity> {
        return salesDao.getSales()
    }
}
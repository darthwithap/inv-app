package me.darthwithap.invapp.data.local.source

import android.content.Context
import me.darthwithap.invapp.data.local.InvDatabase

class InvoiceDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    private val invoiceDao = db.invoiceDao()

//    suspend fun insertInvoice(invoice: InvoiceEntity) {
//        invoiceDao.insertInvoice(invoice)
//    }
}

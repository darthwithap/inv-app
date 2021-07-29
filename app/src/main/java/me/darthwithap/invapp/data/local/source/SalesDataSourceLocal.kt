package me.darthwithap.invapp.data.local.source

import android.content.Context
import me.darthwithap.invapp.data.local.InvDatabase

class SalesDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    private val salesDao = db.salesDao()

//    suspend fun insertSales(sale: SalesInvoiceEntity) {
//        salesDao.insertSales(sale)
//    }
//
//    suspend fun getSales(): List<SalesInvoiceEntity> {
//        return salesDao.getSales()
//    }
}
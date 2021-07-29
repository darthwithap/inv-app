package me.darthwithap.invapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.darthwithap.invapp.data.local.entity.InvoiceEntity
import me.darthwithap.invapp.data.local.entity.SalesInvoiceEntity

@Dao
interface InvoiceDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertInvoice(invoice: InvoiceEntity)
}
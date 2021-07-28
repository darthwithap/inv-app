package me.darthwithap.invapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.darthwithap.invapp.data.local.entity.SalesInvoiceEntity

@Dao
interface SalesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSales(salesInvoice: SalesInvoiceEntity)

    @Query("SELECT * FROM sales_invoice_table")
    suspend fun getSales(): List<SalesInvoiceEntity>
}
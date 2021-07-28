package me.darthwithap.invapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.darthwithap.invapp.data.local.dao.*


@Database(entities = [], version = 1, exportSchema = false)
abstract class InvDatabase : RoomDatabase() {

    abstract fun authDao(): AuthDao
    abstract fun godownDao(): GodownDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun salesDao(): SalesDao
    abstract fun stockDao(): StockDao

    companion object {
        @Volatile
        private var INSTANCE: InvDatabase? = null

        fun getDatabase(context: Context): InvDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): InvDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                InvDatabase::class.java,
                "inv_database"
            ).build()
        }
    }
}
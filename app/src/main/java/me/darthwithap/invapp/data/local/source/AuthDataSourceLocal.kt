package me.darthwithap.invapp.data.local.source

import android.content.Context
import me.darthwithap.invapp.data.local.InvDatabase

private const val TAG = "AuthDataSource"

class AuthDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    val authDao = db.authDao()

//    suspend fun insertUser(loginData: LoginDataEntity) {
//        authDao.insertUser(loginData)
//    }
//
//    suspend fun getLoginData(): LoginDataEntity {
//        return authDao.getLoginData()
//    }
//
//    suspend fun getCurrentUser(): UserEntity {
//        return authDao.getCurrentUser()
//    }
}
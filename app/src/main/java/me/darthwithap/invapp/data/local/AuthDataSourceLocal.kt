package me.darthwithap.invapp.data.local

import android.content.Context
import me.darthwithap.api.InvApiClient
import me.darthwithap.api.InvApiClient.api
import me.darthwithap.invapp.utils.Result
import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.api.models.responses.LoginResponse
import me.darthwithap.api.models.responses.LogoutResponse
import me.darthwithap.invapp.data.local.dao.AuthDao
import me.darthwithap.invapp.data.local.entity.LoginDataEntity
import me.darthwithap.invapp.data.local.entity.UserEntity
import org.json.JSONObject
import java.io.IOException

private const val TAG = "AuthDataSource"

class AuthDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    val authDao = db.authDao()

    suspend fun insertUser(loginData: LoginDataEntity) {
        authDao.insertUser(loginData)
    }

    suspend fun getLoginData(): LoginDataEntity {
        return authDao.getLoginData()
    }

    suspend fun getCurrentUser(): UserEntity {
        return authDao.getCurrentUser()
    }
}
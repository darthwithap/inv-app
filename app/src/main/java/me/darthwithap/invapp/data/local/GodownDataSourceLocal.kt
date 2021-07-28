package me.darthwithap.invapp.data.local

import android.content.Context
import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.requests.NewGodownRequest
import me.darthwithap.api.models.responses.GodownsResponse
import me.darthwithap.api.models.responses.NewGodownResponse
import me.darthwithap.invapp.data.local.entity.GodownEntity
import me.darthwithap.invapp.data.local.entity.LoginDataEntity
import me.darthwithap.invapp.data.local.entity.UserEntity
import me.darthwithap.invapp.utils.Result
import org.json.JSONObject
import java.io.IOException

class GodownDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    private val godownDao = db.godownDao()

    suspend fun insertNewGodown(godown: GodownEntity) {
        godownDao.insertNewGodown(godown)
    }

    suspend fun getAllGodowns(): List<GodownEntity> {
        return godownDao.getAllGodowns()
    }
}
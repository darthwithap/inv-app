package me.darthwithap.api

import me.darthwithap.api.client.InvApi
import me.darthwithap.api.client.InvAuthApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object InvApiClient {

    private var _authToken: String? = null
    private val okhttpInterceptor = Interceptor { chain ->
        var req = chain.request()

        // TODO add Bearer Token here

        _authToken?.let {
            req = req.newBuilder()
                .header("Authorization", "Bearer $it")
                .build()
        }
        chain.proceed(req)
    }

    //val authToken get() = _authToken

    fun setAuthToken(token: String?) {
        _authToken = token
    }

    private val okhttp = OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("http://15.206.210.139:2000/")
        .addConverterFactory(MoshiConverterFactory.create())

    val api: InvApi = retrofitBuilder
        .client(okhttp.build())
        .build()
        .create(InvApi::class.java)

    val authApi: InvAuthApi = retrofitBuilder
        .client(okhttp.addInterceptor(okhttpInterceptor).build())
        .build()
        .create(InvAuthApi::class.java)
}
package com.rigo.simondice.domain.service.network


import com.rigo.simondice.domain.models.TopResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface IApiService {
    companion object {
        const val BASE_URL = "https://simon-game-api-production.up.railway.app/"
    }
    @Headers("Authorization: Bearer b04f65b8835a9462961ebd04861b8be83c5314d81225ebe7c04e17ecb353d0f9d5dfbb6838a31004c2427ee417eb7dab838677f2417c20b9054b99f05792e5d09ce3e4cf0aed7d108c48f80ccf14e446fb19f514bbf930fefd6fff2c0708922aee47a403c4b47c84b0159a52a8789eec7cf47d55cb38e855424ad3932cb6b318")

    @GET("api/tops")
    suspend fun getTop(): TopResponse
}
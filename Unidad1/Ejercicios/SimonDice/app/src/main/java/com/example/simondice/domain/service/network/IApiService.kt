package com.example.simondice.domain.service.network

import com.example.simondice.domain.models.PostRequestTop
import com.example.simondice.domain.models.TopResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IApiService {
    companion object {
        const val BASE_URL = "https://simon-game-api-production.up.railway.app/"
        const val ACCES_TOKEN = "1a24f147411000a4ae480660ffb9463f2ca415ef08802618140d62daa5a14f8732e98026f6c9ed5e36e6742cc264945144454ee9753335b4fc3d737a7478be6674e4bea7eb67c1d98ff4818239acc5ce82352784d4fce68842ea3c5f112055af6f4b1dc79fcdd89a2cb5997b4e2eac9596f5b35515474c323c9595eaf0229ce1"
    }


    @Headers("Authorization: Bearer $ACCES_TOKEN")
    @GET("api/tops")
    suspend fun getTop(): TopResponse

    @Headers("Authorization: Bearer $ACCES_TOKEN")
    @POST("api/tops")
    suspend fun postTop(@Body requestTop: PostRequestTop): TopResponse
}
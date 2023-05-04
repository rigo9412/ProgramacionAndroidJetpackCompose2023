package com.example.simondicef

import com.example.simondicef.domain.models.getresponsetop.GetResponseAllTop
import com.example.simondicef.domain.models.getresponsetop.GetResponseTop
import com.example.simondicef.domain.models.postrequesttop.PostRequestTop
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IApiService {
    companion object {
        const val BASE_URL = "https://simon-game-api-production.up.railway.app"
        const val ACCESS_TOKEN = "b2329f59230f78668356a740f97953617b4f284b8e2d1d8f1968335abe87729ffa1905a7b3ccbc2e507091bd10a97e88cb37b6b7d5d8e935737b514f0d6b91ee3e14e33e79c0f717701891987951edfd818ad0889eafd035464edd2d70ad86922e44f64345a651e185ca792bfc76487b3355aed002c8037c957b8b9f0ad9914d"
    }

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("api/tops")
    suspend fun getTop(): GetResponseAllTop

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @POST("api/tops")
    suspend fun postTop(@Body requestTop: PostRequestTop): GetResponseTop
    }
}
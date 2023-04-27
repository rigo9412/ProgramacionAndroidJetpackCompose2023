package com.rigo.simondice.domain.service.network


import com.rigo.simondice.domain.models.getresponsetop.GetResponseAllTop
import com.rigo.simondice.domain.models.getresponsetop.GetResponseTop
import com.rigo.simondice.domain.models.postrequesttop.PostRequestTop
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IApiService {
    companion object {
        const val BASE_URL = "https://simon-game-api-production.up.railway.app/"
        const val ACCESS_TOKEN = "800315f60c82bf583d60a7c57b54409695e8cf5d12e8e6a7c71d60256a2ce8bfa466e47f335467f2caeb657b058b2e8ca4be73b3830f400af18427bbf18337e14808d118ba58f11d03db0a873d72bf85eec16a150a002bb896e05e3f92763b8cdb40bba7a39e4f2312b1098a3df9ced2f02b37644acfa6cba516f0f7cbeabc74"
    }
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("api/tops")
    suspend fun getTop(): GetResponseAllTop

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @POST("api/tops")
    suspend fun postTop(@Body requestTop: PostRequestTop): GetResponseTop
}
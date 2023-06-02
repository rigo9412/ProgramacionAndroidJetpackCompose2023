package com.tec.pokedexapp.domain.source

import com.tec.pokedexapp.data.model.ApiPostItem
import com.tec.pokedexapp.data.model.ApiResponse
import com.tec.pokedexapp.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestDataSource {

    companion object {
        const val KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNpdnJqZG96Y3VoaGdwc25wcmhsIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODQwNDU1MzMsImV4cCI6MTk5OTYyMTUzM30.f96pvS6q2uO-3gPjNUq-lskVmHXDiXH34Ab3cGWA_w0"
    }
    @GET("rest/v1/users")
    @Headers("apikey: $KEY")
    suspend fun getResponse(): ApiResponse

    @GET("rest/v1/top_10")
    @Headers("apikey: $KEY")
    suspend fun getTop10(): ApiResponse

    @POST("rest/v1/users")
    @Headers("apikey: $KEY")
    suspend fun postTop(@Body data: ApiPostItem)
}
//,"Prefer: return=representation"
package com.tec.pokedexapp.domain.source

import com.tec.pokedexapp.data.model.ApiResponse
import com.tec.pokedexapp.data.model.ApiResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestDataSource {
    @GET("users.json")
    suspend fun getResponse(): ApiResponse

    @POST("users.json")
    suspend fun postTop(@Body data: ApiResponseItem)
}
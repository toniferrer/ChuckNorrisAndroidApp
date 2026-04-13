package com.toniferrer.chucknorrisapp2.conexion

import com.toniferrer.chucknorrisapp2.ChuckNorrisBroma
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ChuckApi {
    private const val BASE_URL = "https://api.chucknorris.io/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service: ChuckApi by lazy {
        retrofit.create(ChuckApi::class.java)
    }

    interface ChuckApi {
        @GET("jokes/random")
        suspend fun getBroma(): ChuckNorrisBroma

        @GET("jokes/categories")
        suspend fun getCategorias(): List<String>

        @GET("jokes/random")
        suspend fun getBromaPorCategoria(@Query("category") category: String): ChuckNorrisBroma
    }
}
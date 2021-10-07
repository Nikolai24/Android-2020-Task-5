package com.example.catretrofit

import com.example.catretrofit.data.ApiData
import com.example.catretrofit.data.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CatsApi {

    @GET("v1/images/search?limit=12&page=0&order=Desc")
    suspend fun getListOfCats(): List<ApiData>
}

object CatsApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com/")
        .build()

    private val catsService = retrofit.create(CatsApi::class.java)

    suspend fun getListOfCats(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catsService.getListOfCats()
                .map { result ->
                    Cat(
                        result.id,
                        result.imageUrl
                    )
                }
        }
    }
}
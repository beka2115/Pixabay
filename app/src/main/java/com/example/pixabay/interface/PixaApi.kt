package com.example.pixabay.`interface`

import com.example.pixabay.models.PixaModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun searchImage(
        @Query("q") keyWord: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int=3,
        @Query("key") key: String = "32420363-3f800d04dffa0c52a51ac2dd0"
    ): Call<PixaModel>
}
package com.example.obligatoriskopgave.Repository

import com.example.obligatoriskopgave.Models.Item
import retrofit2.Call
import retrofit2.http. *

interface ReferbService {
    @GET("SalesItem")
    fun getAllSalesItems(): Call<List<Item>>

    @POST("SalesItem")
    fun postSaleItem(@Body item: Item): Call<Item>

    @DELETE("SalesItem/{id}")
    fun deleteItem(@Path ("id") id: Int): Call<Item>
}
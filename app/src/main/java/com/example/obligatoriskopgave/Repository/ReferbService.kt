package com.example.obligatoriskopgave.Repository

import com.example.obligatoriskopgave.Models.Item
import retrofit2.Call
import retrofit2.http. *

interface ReferbService {
    @GET("SalesItems")
    fun getAllSalesItems(): Call<List<Item>>

    @POST("SalesItems")
    fun postSaleItem(@Body item: Item): Call<Item>

    @DELETE("SalesItems/{id}")
    fun deleteItem(@Path ("id") id: Int): Call<Item>
}
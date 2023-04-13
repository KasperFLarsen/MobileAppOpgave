package com.example.obligatoriskopgave.Models

import java.io.Serializable
import java.text.DateFormat

data class Item(
    val id: Int,
    val description: String,
    val price: Int,
    val sellerEmail: String,
    val sellerPhone: String,
    val time: Long,
    val pictureUrl: String, var displaytype: String? = null
) : Serializable {
    constructor(
        description: String,
        price: Int,
        sellerEmail: String,
        sellerPhone: String,
        time: Long,
        pictureUrl: String
    ): this( - 1, description, price, sellerEmail, sellerPhone, time, pictureUrl)

    override fun toString(): String{
        return when (displaytype){
            "description"-> "$description"
            "price" -> "$price"
            else -> "$id, $description, $price, $sellerEmail,$sellerPhone, $time, $pictureUrl"
        }
    }

    fun realTime(): String {
        val formatter=DateFormat.getDateInstance()
        return formatter.format(time*1000L)
    }
}
package com.example.obligatoriskopgave.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.obligatoriskopgave.Models.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemsRepository {
    private val url = "https://anbo-salesitems.azurewebsites.net/api/"

    private val referbService: ReferbService

    val itemlivedata: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val updateMessageLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        val build: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        referbService = build.create(ReferbService::class.java)
        getAllSalesItems()
    }

    fun getAllSalesItems() {
        referbService.getAllSalesItems().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    val b: List<Item>? = response.body()
                    itemlivedata.postValue(b!!)
                    errorMessageLiveData.postValue("")
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("Samsung", message)
                }

            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("Samsung", t.message!!)
            }
        })

    }

    fun add(item: Item) {
        referbService.postSaleItem(item).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                if (response.isSuccessful) {
                    Log.d("Samsung", "added: " + response.body())
                    updateMessageLiveData.postValue("added: " + response.body())
                    getAllSalesItems()
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("Samsung", message)
                }

            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("Samsung", t.message!!)
            }

        })
    }

    fun deleteItem(id: Int) {
        referbService.deleteItem(id).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                if (response.isSuccessful) {
                    Log.d("Samsung", "deleted: " + response.body())
                    updateMessageLiveData.postValue("deleted: " + response.body())
                    getAllSalesItems()
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("Samsung", message)
                }

            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("Samsung", t.message!!)
            }

        })
    }

    fun sortByDescription() {
        itemlivedata.value = itemlivedata.value?.sortedBy { it.description }

    }

    fun sortByDescriptionDescending() {
        itemlivedata.value = itemlivedata.value?.sortedByDescending { it.description }

    }

    fun filterByDescription(description: String) {
        if (description.isBlank()) {
            getAllSalesItems()
        } else {
            itemlivedata.value = itemlivedata.value?.filter { item ->
                item.description.contains(description)
            }
        }
    }



    fun sortByPrice() {
        itemlivedata.value = itemlivedata.value?.sortedBy { it.price }
    }
    fun sortByPriceDescending(){
        itemlivedata.value = itemlivedata.value?.sortedByDescending { it.price }
    }






}

package com.example.obligatoriskopgave.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.obligatoriskopgave.Repository.ItemsRepository

class ItemViewModel: ViewModel() {
    private val repository= ItemsRepository()
    val itemslivedata: LiveData <List<Item>> = repository.itemlivedata
    val errormessagelivedata: LiveData <String> = repository.errorMessageLiveData
    val updatemessagelivedata: LiveData <String> = repository.updateMessageLiveData



    init {
        reload()
    }
    fun reload() {
        repository.getAllSalesItems()
    }

    operator fun get(index: Int): Item? {
        return itemslivedata.value?.get(index)
    }

    fun add(item: Item) {
        repository.add(item)
    }

    fun deleteitem(id: Int) {
        repository.deleteItem(id)
    }
    fun sortByDescription() {
        repository.sortByDescription()
    }

    fun sortByDescriptionDescending() {
        repository.sortByDescriptionDescending()
    }



    fun filterByDescription(description: String) {
        repository.filterByDescription(description)
    }

    fun sortByPrice() {
        repository.sortByPrice()
    }

    fun sortByPriceDescending() {
        repository.sortByPriceDescending()
    }


}
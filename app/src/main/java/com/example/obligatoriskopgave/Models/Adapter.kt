package com.example.obligatoriskopgave.Models


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.obligatoriskopgave.R
import kotlinx.coroutines.NonDisposableHandle.parent


class Adapter<T : Item>(
    private val item: List<T>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<Adapter.ItemViewHolder>() {


    override fun getItemCount(): Int {
        return item.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listed_item, parent, false)
        return ItemViewHolder(view, onItemClicked)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = item[position]
        item.displaytype = "description"
        holder.listedItemDescriptionTextView.text = when (item.displaytype) {
            "description" -> item.description
            "price" -> ""
            else -> item.toString()
        }
        item.displaytype = "price"
        holder.listedItemPriceTextView.text = when (item.displaytype) {
            "description" -> ""
            "price" -> item.price.toString()
            else -> ""
        }
    }

    class ItemViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val listedItemDescriptionTextView: TextView =
            itemView.findViewById(R.id.description_textview)
        val listedItemPriceTextView: TextView =
            itemView.findViewById(R.id.price_textview)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition

            onItemClicked(position)
        }
    }

}


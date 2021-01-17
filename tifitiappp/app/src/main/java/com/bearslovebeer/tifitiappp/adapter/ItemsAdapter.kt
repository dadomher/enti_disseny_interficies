package com.bearslovebeer.tifitiappp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.models.Items2
import com.bearslovebeer.tifitiappp.models.ListItems
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_objects.view.*

class ItemsAdapter (val items:ArrayList<Items2>):RecyclerView.Adapter<ItemsAdapter.ItemsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ItemsHolder(layoutInflater.inflate(R.layout.item_objects, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.render(items[position])
    }

    class ItemsHolder(val view: View):RecyclerView.ViewHolder(view) {

        fun render(items: Items2) {
            Log.d("porfii", "Size: ${items.data.size}")
           // view.itemText.text = items.data.component2().toString()
            Picasso.get().load(items.data.component2().toString()).into(view.itemBanner)
        }
    }
}
package com.bearslovebeer.tifitiappp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.models.ListItems
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_objects.view.*

class ObjectsAdapter (val items: ListItems):RecyclerView.Adapter<ObjectsAdapter.ItemsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ItemsHolder(layoutInflater.inflate(R.layout.item_objects, parent, false))
    }

    override fun getItemCount(): Int = items.data.size

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.render(items, position)
    }

    class ItemsHolder(val view: View):RecyclerView.ViewHolder(view) {
        fun render(items: ListItems, position: Int) {
            Picasso.get().load(items.data.get(position).imgUrl).into(view.itemBanner)
        }
    }
}
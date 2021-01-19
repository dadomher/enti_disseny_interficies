package com.bearslovebeer.tifitiappp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.models.ListItems
import com.bearslovebeer.tifitiappp.models.ListItems_v2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_objects.view.*

class ObjectsAdapter (val items: ListItems_v2, val nameObject: Int):RecyclerView.Adapter<ObjectsAdapter.ItemsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ItemsHolder(layoutInflater.inflate(R.layout.item_objects, parent, false))
    }


    override fun getItemCount(): Int = items.belt.size

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        when (nameObject) {
            0 -> holder.renderSword(items, position)
            1 -> holder.renderBow(items, position)
            2 -> holder.renderRod(items, position)
            3 -> holder.renderTear(items, position)
            4 -> holder.renderVest(items, position)
            5 -> holder.renderCoat(items, position)
            6 -> holder.renderBelt(items, position)
            7 -> holder.renderGloves(items, position)
            8 -> holder.renderSpatula(items, position)
        }
    }

    class ItemsHolder(val view: View):RecyclerView.ViewHolder(view) {
        fun renderSword(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.sword.get(position).imgUrl).into(view.itemBanner)
        }
        fun renderBow(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.bow.get(position).imgUrl).into(view.itemBanner)
        }
        fun renderRod(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.rod.get(position).imgUrl).into(view.itemBanner)
        }
        fun renderTear(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.tear.get(position).imgUrl).into(view.itemBanner)
        }
        fun renderVest(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.vest.get(position).imgUrl).into(view.itemBanner)
        }
        fun renderCoat(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.coat.get(position).imgUrl).into(view.itemBanner)
        }
        fun renderBelt(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.belt.get(position).imgUrl).into(view.itemBanner)
        }
        fun renderGloves(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.gloves.get(position).imgUrl).into(view.itemBanner)
        }
        fun renderSpatula(items: ListItems_v2, position: Int) {
            Picasso.get().load(items.spatula.get(position).imgUrl).into(view.itemBanner)
        }
    }
}
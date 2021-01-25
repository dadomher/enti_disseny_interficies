package com.bearslovebeer.tifitiappp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.models.ListChannels

class ChannelsAdapter (val channel: ListChannels): RecyclerView.Adapter<ChannelsAdapter.ItemsHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelsAdapter.ItemsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        context = parent.context

        return ChannelsAdapter.ItemsHolder(
            layoutInflater.inflate(
                R.layout.fragment_twitch,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = channel.data.size

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.render(channel, context)
    }

    class ItemsHolder(val view: View):RecyclerView.ViewHolder(view) {
        fun render(channel: ListChannels, context: Context) {

        }
    }


}
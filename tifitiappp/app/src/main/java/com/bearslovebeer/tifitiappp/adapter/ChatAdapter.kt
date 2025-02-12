package com.bearslovebeer.tifitiappp.adapter

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.models.Chat
import kotlinx.android.synthetic.main.item_chat.view.*

class ChatAdapter(var chatList: List<Chat>): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    // Inflate view (xml layout) -> ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val  chatView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)

        return ChatViewHolder(chatView)
    }

    // Update view position
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]

        holder.messageTextView.text = chat.message
        holder.usernameTextView.text = chat.username
        holder.dateTextView.text = chat.sentAt
    }

    // Total items
    override fun getItemCount(): Int {
        return chatList.count()
    }


    // Map view xml -> kotlin
    inner class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // Init Views
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
        val usernameTextView: TextView = view.findViewById(R.id.usernameTextView)
        val dateTextView: TextView = view.findViewById(R.id.messageDateTextView)
    }
}
package com.bearslovebeer.tifitiappp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bearslovebeer.tifitiappp.Constants.COLLECTION_CHAT
import com.bearslovebeer.tifitiappp.Constants.COLLECTION_USERS
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.adapter.ChatAdapter
import com.bearslovebeer.tifitiappp.models.Chat
import com.bearslovebeer.tifitiappp.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ChatFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var firestore: FirebaseFirestore
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init Firestore
        firestore = Firebase.firestore
        // Init Views
        initViews(view)
        // Init RecyclerView
        initRecyclerView()
        // Init Listeners
        initListeners()
        // Get Chats
        getChats()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        messageEditText = view.findViewById(R.id.messageEditText)
        sendButton = view.findViewById(R.id.sendButton)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
    }

    private fun initRecyclerView() {
        //Layout Manager
        var layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        //Adapter
        chatAdapter = ChatAdapter(chatList = listOf())
        recyclerView.adapter = chatAdapter
    }

    private fun initListeners() {
        // Send Button
        sendButton.setOnClickListener {
            val message = messageEditText.text.toString()

            // Validate
            if (message.isBlank()) return@setOnClickListener
            // Create Chat Message in Firestore
            sendMessage(message)
        }

        //Swipe to Refresh
        swipeRefreshLayout.setOnRefreshListener {
            getChats()
        }
    }

    private fun sendMessage(message: String) {
        // 0 - Get User ID
        Firebase.auth.currentUser?.uid?.let { userId: String ->
            // User Available
            // 1 - Get User Object
            firestore
                .collection(COLLECTION_USERS)
                .document(userId)
                .get()
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        val user = it.result?.toObject(User::class.java)?.let { user: User ->

                            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

                            // 2 - Create Chat Message
                            val chat = Chat(
                                userId = Firebase.auth.currentUser?.uid,
                                message = message,
                                sentAt = sdf.format(Date()),
                                isSent = false,
                                imageUrl = null,
                                username =user.username,
                                avatarUrl = null // TODO: Support User Avatar
                            )
                            // 3 - Save chat in Firestore
                            firestore
                                .collection(COLLECTION_CHAT)
                                .add(chat)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Log.i("Chat", "Succes uploding message $message")
                                        // Update Chats
                                        getChats()
                                        messageEditText.setText("") //put empty chat
                                    } else {
                                        Log.i("Chat", "Error uploading message $message")
                                        // TODO: Show error
                                    }
                                }

                        } ?: kotlin.run {
                            // TODO: Show error
                        }
                    } else {
                        // TODO: Show error
                    }
                }

        } ?: run {
            // User not Available
            // TODO: Show message
        }
    }

    private fun getChats() {
        // TODO: Sort
        swipeRefreshLayout.isRefreshing = true

        firestore
            .collection(COLLECTION_CHAT)
            .orderBy("sentAt", Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Update UI
                    //Agafo tots el documents i els converteixo en un objecte chat cada un d'ells o  retorno una llista buida
                    val chats: List<Chat> = it.result?.documents?.mapNotNull {
                        it.toObject(Chat::class.java)
                    }.orEmpty()
                    chatAdapter.chatList = chats
                    // Notifica que se ha alterado la lista
                    chatAdapter.notifyDataSetChanged()
                } else {
                    // TODO: Show error
                }
                swipeRefreshLayout.isRefreshing = false
            }
    }
}
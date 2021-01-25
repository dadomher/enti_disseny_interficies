package com.bearslovebeer.tifitiappp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bearslovebeer.tifitiappp.Constants
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.activity.WebViewActivity
import com.bearslovebeer.tifitiappp.adapter.ChannelsAdapter
import com.bearslovebeer.tifitiappp.adapter.ObjectsAdapter
import com.bearslovebeer.tifitiappp.models.Channel
import com.bearslovebeer.tifitiappp.models.ListChannels
import com.bearslovebeer.tifitiappp.services.NetworkManager
import com.bearslovebeer.tifitiappp.services.UserManager
import com.google.gson.Gson
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_objects.*
import kotlinx.android.synthetic.main.fragment_twitch.*
import kotlinx.android.synthetic.main.fragment_twitch.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class TwitchFragment : Fragment() {

    private lateinit var twitchLoginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitch, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        getTopGames()
    }

    override fun onResume() {
        super.onResume()
        checkUserAvalability()
    }

    private fun initViews(view: View) {
        twitchLoginButton = view.findViewById<Button>(R.id.twitchLoginButton)
    }

    private fun initListeners() {
        twitchLoginButton.setOnClickListener{
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("FRAGMENT", "TWITCH")
            startActivity(intent)
        }
    }

    private fun checkUserAvalability() {
        val isLoggedIn = UserManager(requireContext()).getAccesToken() != null
        if(isLoggedIn) {
            twitchLoginButton.visibility = View.GONE
        } else {
            twitchLoginButton.visibility = View.VISIBLE
        }
    }

    private fun getTopGames() {
        val httpClient = NetworkManager.createHttpClient()

        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {

                val accessToken = UserManager(requireContext()).getAccesToken()


                // Get Top Games
                try {
                    // QUERY = (GAME_ID(TFT_ID) & LANGUAGE=ESPAÑOL & FIRST=TOP 10 CANALES)

                    var response = httpClient.get<ListChannels>("https://api.twitch.tv/helix/streams?game_id=513143&language=es&first=3") {
                        header("Client-Id", Constants.OAUTH_CLIENT_ID)
                        header("Authorization", "Bearer $accessToken")
                    }

                    Log.i("AVER", response.toString())


                    // Change to Main Thread
                    withContext(Dispatchers.Main) {
                        // TODO: Update UI
                        //initRecycler(response)
                    }
                } catch (t:Throwable) {
                    // TODO: Handle error

                }
            }
        }
    }

    private fun initRecycler(result: ListChannels) {
        twitchRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        var adapter = ChannelsAdapter(result)
        twitchRecyclerView.adapter = adapter
    }
}
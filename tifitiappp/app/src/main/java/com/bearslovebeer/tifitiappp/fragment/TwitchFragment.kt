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
import com.bearslovebeer.tifitiappp.viewmodel.StreamsViewModel
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

    // TODO: Use Android ViewModel
    private val streamsViewModel by lazy { StreamsViewModel(UserManager(requireContext())) }

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
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        streamsViewModel.checkUserAvalability()
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

        /*swipeToRefresh.setOnRefreshListener {
            streamsViewModel.onRefresh()
        }*/
    }

    // TODO: Refactor to ViewModel
    private fun initObservers() {

        streamsViewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn: Boolean ->
            if(isLoggedIn) {
                twitchLoginButton.visibility = View.GONE
            } else {
                twitchLoginButton.visibility = View.VISIBLE
            }
        }

        //streamsViewModel.tftGames.observe(viewLifecycleOwner) {
            // TODO
            // adapter.tftFanes = it
            // adapter.notifyDataSetChanged()
        //}
    }

    private fun initRecycler(result: ListChannels) {
        /*twitchRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        var adapter = ChannelsAdapter(result)
        twitchRecyclerView.adapter = adapter*/
    }
}
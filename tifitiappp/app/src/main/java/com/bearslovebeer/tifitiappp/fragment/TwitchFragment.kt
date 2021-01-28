package com.bearslovebeer.tifitiappp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.activity.WebViewActivity
import com.bearslovebeer.tifitiappp.models.ListChannels
import com.bearslovebeer.tifitiappp.services.UserManager
import com.bearslovebeer.tifitiappp.viewmodel.StreamsViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Tifiti Manager - Twitch";
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
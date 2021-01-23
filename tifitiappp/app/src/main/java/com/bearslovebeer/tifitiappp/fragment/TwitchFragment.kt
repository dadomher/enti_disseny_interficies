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

class TwitchFragment : Fragment() {

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

        view.findViewById<Button>(R.id.twitchLoginButton).setOnClickListener{
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("FRAGMENT", "TWITCH")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

    }
}
package com.bearslovebeer.tifitiappp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import com.bearslovebeer.tifitiappp.fragment.chatFragment
import com.bearslovebeer.tifitiappp.fragment.profileFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //Create our five fragments object
    lateinit var newsFragment: newsFragment
    lateinit var objectsFragment: objectsFragment
    lateinit var champsFragment: champsFragment
    lateinit var chatFragment: chatFragment
    lateinit var profileFragment: profileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //now let's create our framelayout and bottomnav variables
        var bottomnav = findViewById<BottomNavigationView>(R.id.BottomNavMenu)
        var frame:FrameLayout = findViewById<FrameLayout>(R.id.frameLayout)
        //now let's the default fragment
        newsFragment = newsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout,newsFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        //now we will need to create our different fragments
        //now let's add the menu event listener
        bottomnav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.news -> {
                    newsFragment = newsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,newsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.objects -> {
                    objectsFragment = objectsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,objectsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.champs -> {
                    champsFragment = champsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,champsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.chat -> {
                    chatFragment =
                        chatFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,chatFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.options -> {
                    profileFragment = profileFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            true

        }
        // Init ADmob
        MobileAds.initialize(this){}
        // Load Ad
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }
}
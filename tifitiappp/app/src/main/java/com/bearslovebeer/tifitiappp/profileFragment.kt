package com.bearslovebeer.tifitiappp.fragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.activity.RegisterActivity
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class profileFragment : Fragment() {

    private val TAG = "ProfileFragment"
    private val firebaseAn = Firebase.analytics
    // Views
    private lateinit var registerButton: Button
    private lateinit var welcomeTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init Views
        initViews(view)
        // Init Listeners
        initListeners()

        checkUserAvailability()
    }

    override fun onStart() {
        super.onStart()
        checkUserAvailability()
    }

    private fun initViews(parentView: View) {
        registerButton = parentView.findViewById<Button>(R.id.register_button)
        welcomeTextView = parentView.findViewById(R.id.welcomeTextView)
    }

    private fun initListeners() {
        registerButton.setOnClickListener {
            // Track register button click
            firebaseAn.logEvent("registerButtonClick", null)

            /*val bundle = Bundle()
            bundle.putString(firebaseAn.Param.METHOD, method)
            firebaseAn.logEvent(firebaseAn.Event.SIGN_UP, bundle)*/
            // Open Register Activity
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Updates UI depending if user is available or not
    private fun checkUserAvailability() {
        Firebase.auth.currentUser?.let {
            // User available
            registerButton.visibility = View.GONE
            welcomeTextView.visibility = View.VISIBLE
        } ?: run {
            // User not available
            registerButton.visibility = View.VISIBLE
            welcomeTextView.visibility = View.GONE
        }
    }
}
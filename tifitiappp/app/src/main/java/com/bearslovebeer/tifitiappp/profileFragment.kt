package com.bearslovebeer.tifitiappp.fragment
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bearslovebeer.tifitiappp.Constants
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.activity.RegisterActivity
import com.bearslovebeer.tifitiappp.models.User
import com.bumptech.glide.Glide
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.internal.bind.DateTypeAdapter
import java.io.ByteArrayOutputStream

class profileFragment : Fragment() {

    private val TAG = "ProfileFragment"
    private val firebaseAn = Firebase.analytics
    private val REQUEST_IMAGE_CAPTURE = 100

    // Views
    private lateinit var registerButton: Button
    private lateinit var welcomeTextView: TextView
    private lateinit var avatarButton: Button
    private lateinit var avatarImageView: ImageView

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
    }

    override fun onStart() {
        super.onStart()
        checkUserAvailability()
    }

    private fun initViews(parentView: View) {
        registerButton = parentView.findViewById<Button>(R.id.register_button)
        welcomeTextView = parentView.findViewById(R.id.welcomeTextView)
        avatarButton = parentView.findViewById<Button>(R.id.avatarButton)
        avatarImageView = parentView.findViewById(R.id.avatarImageView)
    }

    private fun initListeners() {
        registerButton.setOnClickListener {
            // Track register button click
            Firebase.analytics.logEvent("registerButtonClick", null)

            // Open Register Activity
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }
        avatarButton.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // DISPLAY ERROR STATE TO THE USER
        }
    }
    private fun uploadImageToFirebaseStorage(bitmap: Bitmap) {
        val storage = Firebase.storage

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        // Obtain Firebase Storage reference
        val userId = Firebase.auth.currentUser?.uid
        val storageReference = storage.reference.child("image/avatars/$userId.jpg")

        // Upload to Firebase Storage
        storageReference.putBytes(data)
            .addOnSuccessListener { taskSnapshot ->
                // Obtain image URL
                storageReference.downloadUrl
                    .addOnSuccessListener { uri ->
                        val url = uri.toString()
                        // Set to user profile in Firestore
                        Firebase. firestore
                            .collection(Constants.COLLECTION_USERS)
                            .document(userId!!)
                            .update("avatarUrl", url)
                    }.addOnFailureListener {

                    }
                // Set to user profile in Firestore

            }.addOnFailureListener {

            }
    }

    // Updates UI depending if user is available or not
    private fun checkUserAvailability() {
        Firebase.auth.currentUser?.let { user ->
            // User available
            registerButton.visibility = View.GONE
            welcomeTextView.visibility = View.VISIBLE

            // Get User Profile
            Firebase.firestore
                .collection(Constants.COLLECTION_USERS)
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    it.toObject(User::class.java)?.let { user ->
                        val avatarUrl = user.avatarUrl

                        // Load avatar URL into ImageView
                            // 1 - Open URL
                            // 2 - Download file
                            // 3 - Load file into ImageView
                            // * - Cache
                            // * - Delete cache (30 days)
                        Glide.with(avatarImageView)
                            .load(avatarUrl)
                            .into(avatarImageView)
                    }
                }
        } ?: run {
            // User not available
            registerButton.visibility = View.VISIBLE
            welcomeTextView.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            (data?.extras?.get("data") as? Bitmap)?.let { bitmap ->
                avatarImageView.setImageBitmap(bitmap)
                uploadImageToFirebaseStorage(bitmap)
            }
        }
    }
}
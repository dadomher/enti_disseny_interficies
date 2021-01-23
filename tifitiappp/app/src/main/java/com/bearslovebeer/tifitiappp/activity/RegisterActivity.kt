package com.bearslovebeer.tifitiappp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.bearslovebeer.tifitiappp.Constants
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private val TAG = "RegisterActivity"

    // Firebase Auth
    private lateinit var auth: FirebaseAuth

    // Firebase Firestore
    private lateinit var firestore: FirebaseFirestore

    private val MIN_PASSWORD_LENGTH = 6

    //Views
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var usernameEditText: EditText
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initi Views
        initViews()
        // Init Listeners
        initListeners()

        // Initialize Firebase Auth
        auth = Firebase.auth
        //Initialize Firebase Firestore
        firestore = Firebase.firestore
    }

    private fun initViews() {
        emailEditText = findViewById<EditText>(R.id.emailEditText)
        passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        registerButton = findViewById<Button>(R.id.registerButton)
        usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
    }

    private fun initListeners() {
        val registerButton : Button = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            // TODO: Get username, email and password from EditTexts
            // Username is not EMPTY
            val username = usernameEditText.text.toString()
            if(username.isBlank()) { // "     "
                usernameEditText.error = getString(R.string.msg_username_null)
                return@setOnClickListener
            }

            // Get email and password from EditTexts
            val email = emailEditText.text.toString()
            // Validate EMAIL is Valid
            if(!isEmailValid(email)) {
                Log.i(TAG,getString(R.string.error_mail_invalid))
                //showMessage(getString(R.string.error_mail_invalid))
                emailEditText.error = getString((R.string.error_mail_invalid))
                return@setOnClickListener
            }

            val password = passwordEditText.text.toString()
            // Validate password
            if(!isPasswordValid(password)) {
                Log.i(TAG, getString(R.string.error_password_invalid))
                //showMessage(getString(R.string.error_password_invalid))
                passwordEditText.error = getString(R.string.error_password_invalid, MIN_PASSWORD_LENGTH)
                return@setOnClickListener
            }

            //Register user
            registerUser(email,password, username)
        }
    }

    private fun registerUser(email: String, password: String, username: String) {
        // Show loading
        progressBar.visibility = View.VISIBLE
        registerButton.isEnabled = false

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    //After 2 seconds, this will be called with the result
                    if(it.isSuccessful) {
                        // TODO: User registered
                        Log.i(TAG, getString(R.string.msg_user_registered))
                        //showMessage()

                        // TODO: 1 - Create User profile (BDD) [userID + username]
                        auth.currentUser?.uid?.let { userID ->  //cuando se completa cojo el id del usuario
                            // Create User Model
                            val user = User(userId = userID, username = username)
                            // Set Document to Firestone
                            firestore
                                    .collection(Constants.COLLECTION_USERS)
                                    .document(userID)
                                    .set(user)
                                .addOnCompleteListener {
                                    //  Finish
                                    progressBar.visibility = View.GONE
                                    registerButton.isEnabled = true
                                        finish()
                                    if(it.isSuccessful) {
                                        Log.i(TAG, getString(R.string.msg_profile_created))
                                    } else {
                                        // Retry
                                        Log.i(TAG, getString(R.string.msg_profile_error))
                                        //showMessage("Error signin up ${it.exception?.message ?:""}")
                                    }
                                }
                        } ?: kotlin.run {
                            Log.i(TAG, getString(R.string.msg_userID_null))
                            //showMessage("Error signin up ${it.exception?.message ?:""}")
                            //  Hide loading
                            progressBar.visibility = View.GONE
                            registerButton.isEnabled = true
                        }
                    } else {
                        // TODO: Handle error
                        Log.i(TAG, "Error: ${it.exception}")
                        //showMessage("Error signing up ${it.exception?.message ?: ""}")
                        //  Hide loading
                        progressBar.visibility = View.GONE
                        registerButton.isEnabled = true

                    }
                }
        //Do other thins

    }

    private fun isEmailValid(email: String) : Boolean {
        val emailRegex = "[a-zA-Z0-9\\+\\.\\_\\%\\-+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"

        //"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$"

        return email.isNotBlank()
                && email.contains("@")
                && email.contains(Regex(emailRegex))
        // xxxxxx@yyyyyy.zzz
    }

    private fun isPasswordValid(password: String) : Boolean {
        // TODO:
        // 1 - No buit
        return password.isNotBlank()
        // 2 - Min 6 characters
                && password.count() >= MIN_PASSWORD_LENGTH
        // 3 - Contains letters & numbers
                && containsLetterAndNumber(password)
    }

    private fun containsLetterAndNumber (text: String) : Boolean {
        var containsLetter = false
        var containsNumber = false

        text.forEach {
            if(it.isDigit()) {
                containsNumber = true
            }
            if(it.isLetter()) {
                containsLetter = true
            }
        }
        return containsLetter && containsNumber
    }

    private fun showMessage(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
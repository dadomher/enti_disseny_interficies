package com.bearslovebeer.tifitiappp.data

import com.bearslovebeer.tifitiappp.Constants.COLLECTION_USERS
import com.bearslovebeer.tifitiappp.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserFirestoreDataSource  {
    fun getUser(userId: String, resultListener :((User?)->Unit)) {
        // TODO: return user
        Firebase.firestore
            .collection(COLLECTION_USERS)
            .document(userId)
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    val user = it.result?.toObject(User::class.java)
                    resultListener(user)
                    // TODO: return
                } else {
                    resultListener(null)
                }
            }
    }
}
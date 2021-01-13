package com.bearslovebeer.tifitiappp.data

import android.content.Context
import com.bearslovebeer.tifitiappp.model.User

class UserRepository (
    private val firestoreDataSource: UserFirestoreDataSource = UserFirestoreDataSource(),
    private val localDataSource: UserLocalDataSource = UserLocalDataSource()
) {

    // Tries to get username locally first
    // If not avaible, get from Firestore
    // And save locally
    fun getUsername(context: Context, userId: String, resultListener: ((String?) -> Unit)) {
        localDataSource.getUsername(context)?.let { username ->
            // Return username
            resultListener(username)
        } ?: kotlin.run {
            // TODO: return from Firestore
            firestoreDataSource.getUser(userId) { user: User? ->
                //Save locally
                localDataSource.saveUsername(context, user?.username ?: "")
                // Return result
                resultListener(user?.username)
            }
        }
    }
}
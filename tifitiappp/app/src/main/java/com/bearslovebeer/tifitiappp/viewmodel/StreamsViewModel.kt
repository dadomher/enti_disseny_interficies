package com.bearslovebeer.tifitiappp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.bearslovebeer.tifitiappp.Constants
import com.bearslovebeer.tifitiappp.models.ListChannels
import com.bearslovebeer.tifitiappp.services.NetworkManager
import com.bearslovebeer.tifitiappp.services.UserManager
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Use Android ViewModel
class StreamsViewModel(
    private val userManager: UserManager,
) {

    var isLoggedIn = MutableLiveData<Boolean>()
    // 1 - Emetre nous valors
    // 2 - Escoltant nous valors
    val tftGames = MutableLiveData<List<Any>>()
    val errors = MutableLiveData<String>()

    // region Public methods
    fun checkUserAvalability() {
        val isLoggedIn = userManager.getAccesToken() != null
        this.isLoggedIn.postValue(isLoggedIn)
        if(isLoggedIn) getTopGames()
    }

    fun onRefresh() {
        // TODO: Get TFT Channels
        getTopGames()
    }

    fun logout() {
        this.isLoggedIn.postValue(false)
    }
    // endregion

    // region Private methods

    private fun getTopGames() {
        val httpClient = NetworkManager.createHttpClient()
        // ViewModelScope

        GlobalScope.launch {
                withContext(Dispatchers.IO) {

                    val accessToken = userManager.getAccesToken()

                    // Get Top Games

                    try {
                        // QUERY = (GAME_ID(TFT_ID) & LANGUAGE=ESPAÑOL & FIRST=TOP 10 CANALES)

                        var response = httpClient.get<String>("https://api.twitch.tv/helix/streams?game_id=513143&language=es&first=3") {
                            header("Client-Id", Constants.OAUTH_CLIENT_ID)
                            header("Authorization", "Bearer $accessToken")
                        }

                        Log.i("AVER", response)

                        // Change to Main Thread
                        withContext(Dispatchers.Main) {
                            // TODO: Update UI
                            //initRecycler(response)
                            //tftGames.postValue(listOf(response))
                        }
                    } catch (t:Throwable) {
                        // TODO: Handle error
                        errors.postValue(t.message)
                        Log.i("AVER", t.message.toString())
                    }
                }

        }

    }

    // endregion
}
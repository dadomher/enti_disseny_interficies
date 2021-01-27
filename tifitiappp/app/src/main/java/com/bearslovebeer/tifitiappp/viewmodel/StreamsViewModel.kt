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
    private val userManager: UserManager
) {

    var isLoggedIn = MutableLiveData<Boolean>()

    // 1 - Emetre nous valors
    // 2 - Escoltant nous valors
    val tftGames = MutableLiveData<List<Any>>()
    val errors = MutableLiveData<String>()

    // Query with the 3 best top spanish channels
    var query = "https://api.twitch.tv/helix/streams?game_id=513143&language=es&first=3"

    // Query with the 3 best top channels of tft on the world
    var query1 = "https://api.twitch.tv/helix/streams?game_id=513143&first=3"

    // Query with the first 10 tft spanish games of yesterday
    var query2 = "https://api.twitch.tv/helix/videos?game_id=513143&language=es&first=10&period=day"

    //Query with the first 10 tft games of yesterday on the world
    var query3 = "https://api.twitch.tv/helix/videos?game_id=513143&first=10&period=day"


    // region Public methods
    fun checkUserAvalability() {
        val isLoggedIn = userManager.getAccesToken() != null
        this.isLoggedIn.postValue(isLoggedIn)
        if (isLoggedIn) doAllPetitions()
    }

    fun onRefresh() {
        // TODO: Get TFT Channels
        doAllPetitions()
    }

    fun logout() {
        this.isLoggedIn.postValue(false)
    }
    // endregion

    // region Private methods
    private fun doAllPetitions() {
        getTwitchInformation(query, 0)
        getTwitchInformation(query1, 1)
        getTwitchInformation(query2, 2)
        getTwitchInformation(query3, 3)
    }

    private fun getTwitchInformation(query: String, numberOfQuery: Int) {
        val httpClient = NetworkManager.createHttpClient()
        // ViewModelScope

        GlobalScope.launch {
            withContext(Dispatchers.IO) {

                val accessToken = userManager.getAccesToken()

                // Get Top Games

                try {
                    // QUERY = (GAME_ID(TFT_ID) & LANGUAGE=ESPAÑOL & FIRST=TOP 10 CANALES)

                    var response = httpClient.get<String>(query) {
                        header("Client-Id", Constants.OAUTH_CLIENT_ID)
                        header("Authorization", "Bearer $accessToken")
                    }

                    if (numberOfQuery == 0) Log.i("QueryResult", "3TopSpaChannels: $response")
                    else if (numberOfQuery == 1) Log.i("QueryResult", "3TopWorldChannels: $response")
                    else if (numberOfQuery == 2) Log.i("QueryResult", "Yes10TopSpaChannels: $response")
                    else if (numberOfQuery == 3) Log.i("QueryResult", "Yes10TopWorldChannels: ${response}")

                    // Change to Main Thread
                    withContext(Dispatchers.Main) {
                        //initRecycler(response)
                        //tftGames.postValue(listOf(response))
                    }
                } catch (t: Throwable) {
                    // TODO: Handle error
                    errors.postValue(t.message)
                }
            }
        }
    }

    // endregion
}
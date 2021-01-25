package com.bearslovebeer.tifitiappp.models

import com.google.gson.annotations.SerializedName

class ListChannels(data: ArrayList<Channel>, pagination:List<Channel_v2>) {
    @SerializedName("data")
    var data: ArrayList<Channel> = ArrayList()

    @SerializedName("pagination")
    var pagination: List<Channel_v2> = listOf()
}




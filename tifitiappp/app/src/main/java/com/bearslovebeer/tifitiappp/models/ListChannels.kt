package com.bearslovebeer.tifitiappp.models

import com.google.gson.annotations.SerializedName

data class ListChannels(
    @SerializedName("data") val data: List<Channel>? = null,
    val pagination:Channel_v2? = null
) { }

data class Channel(
    val id: Long = 0,

    @SerializedName("user_id")
    var user_id: Long = 0,

    @SerializedName("user_name")
    var user_name: String? = "",

    @SerializedName("game_id")
    var game_id: Int? = 0,

    @SerializedName("game_name")
    var game_name: String? = "",

    @SerializedName("type")
    var type: String? = "",

    @SerializedName("title")
    var title: String? = "",

    @SerializedName("viewer_count")
    var viewer_count: Int? = 0,

    @SerializedName("started_at")
    var started_at: String? = "",

    @SerializedName("language")
    var language: String? = "0",

    @SerializedName("thumbnail_url")
    var thumbnail_url: String? = "",

    @SerializedName("tag_ids")
    var tag_ids: String? = ""
) { }

data class Channel_v2(
    @SerializedName("cursor")
    var cursor: String? = ""
) { }







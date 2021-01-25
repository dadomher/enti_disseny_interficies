package com.bearslovebeer.tifitiappp.models

import com.google.gson.annotations.SerializedName

class Channel(id: Long, user_id: Long, user_name: String?, game_id: Int?, game_name: String?,
              type: String?, title: String?, viewer_count: Int?, started_at: String?, language: String?,
              thumbnail_url: String?,tag_ids: String?) {
    @SerializedName("id")
    var id: Long = 0

    @SerializedName("user_id")
    var user_id: Long = 0

    @SerializedName("user_name")
    var user_name: String? = ""

    @SerializedName("game_id")
    var game_id: Int? = 0

    @SerializedName("game_name")
    var game_name: String? = ""

    @SerializedName("type")
    var type: String? = ""

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("viewer_count")
    var viewer_count: Int? = 0

    @SerializedName("started_at")
    var started_at: String? = ""

    @SerializedName("language")
    var language: String? = "0"

    @SerializedName("thumbnail_url")
    var thumbnail_url: String? = ""

    @SerializedName("tag_ids")
    var tag_ids: String? = ""
}
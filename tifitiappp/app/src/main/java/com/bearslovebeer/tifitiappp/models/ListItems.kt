package com.bearslovebeer.tifitiappp.models

import com.google.gson.annotations.SerializedName

class ListItems {
    @SerializedName("items")
    var data: ArrayList<Items> = ArrayList()
}
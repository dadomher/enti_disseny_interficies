package com.bearslovebeer.tifitiappp.models

import com.google.gson.annotations.SerializedName

class ListItems_v2 {
    @SerializedName("sword")
    var sword: ArrayList<Items> = ArrayList()

    @SerializedName("bow")
    var bow: ArrayList<Items> = ArrayList()

    @SerializedName("rod")
    var rod: ArrayList<Items> = ArrayList()

    @SerializedName("tear")
    var tear: ArrayList<Items> = ArrayList()

    @SerializedName("vest")
    var vest: ArrayList<Items> = ArrayList()

    @SerializedName("coat")
    var coat: ArrayList<Items> = ArrayList()

    @SerializedName("belt")
    var belt: ArrayList<Items> = ArrayList()

    @SerializedName("gloves")
    var gloves: ArrayList<Items> = ArrayList()

    @SerializedName("spatula")
    var spatula: ArrayList<Items> = ArrayList()
}
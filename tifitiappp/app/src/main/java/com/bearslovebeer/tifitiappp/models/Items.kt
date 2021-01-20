package com.bearslovebeer.tifitiappp.models

import com.google.gson.annotations.SerializedName

class Items {
    @SerializedName("name")
    var name: String = ""

    @SerializedName("imgUrl")
    var imgUrl: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("bonusImgUrl_1")
    var bonusImgUrl_1: String = ""

    @SerializedName("bonusImgUrl_2")
    var bonusImgUrl_2: String = ""

    @SerializedName("passive")
    var passive: String = ""

    @SerializedName("subItem_1")
    var subItem_1: String = ""

    @SerializedName("subItem_2")
    var subItem_2: String = ""
}
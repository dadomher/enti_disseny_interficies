package com.bearslovebeer.tifitiappp.model

data class Chat (
    val userId: String? = null,
    val message: String? = null,
    val sentAt: Long? = null,
    val isSent: Boolean? = null, // one check
    val imageUrl: String? = null, // doble check

    // User
    val username: String? = null,
    val avatarUrl: String? = null


)

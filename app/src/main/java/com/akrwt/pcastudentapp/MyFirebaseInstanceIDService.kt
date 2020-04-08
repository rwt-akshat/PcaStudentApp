package com.akrwt.pcastudentapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseInstanceIDService : FirebaseMessagingService() {
    private val subTo = "Students"

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        FirebaseMessaging.getInstance().subscribeToTopic(subTo)
        Log.i("NEW_TOKEN",p0)
    }
}
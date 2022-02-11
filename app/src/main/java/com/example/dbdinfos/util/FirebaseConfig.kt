package com.example.dbdinfos.util

import com.google.firebase.auth.FirebaseAuth

class FirebaseConfig {
private lateinit var fireUser: FirebaseAuth

fun fireInstance(): FirebaseAuth {

    fireUser = FirebaseAuth.getInstance()

    return fireUser
}

}
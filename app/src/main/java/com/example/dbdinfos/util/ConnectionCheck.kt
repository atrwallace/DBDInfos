package com.example.dbdinfos.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionCheck(context: Context) {

    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

}
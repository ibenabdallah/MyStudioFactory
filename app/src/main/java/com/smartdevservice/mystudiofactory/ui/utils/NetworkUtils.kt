@file:Suppress("DEPRECATION")

package com.smartdevservice.mystudiofactory.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/*
 * Created by ibenabdallah on 24/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

object NetworkUtils {
    fun isConnectingToInternet(context: Context?): Boolean {
        if (context == null) return false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = this.activeNetwork
                network?.let {
                    this.getNetworkCapabilities(network)?.let {
                        return it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    }
                }
            } else {
                val networkInfo = this.allNetworkInfo
                for (tempNetworkInfo in networkInfo) {
                    if (tempNetworkInfo.isConnected) {
                        return true
                    }
                }
            }
        }
        return false
    }
}
package com.example.kotlin

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkObserver(context: Context) : ConnectivityManager.NetworkCallback() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @RequiresApi(Build.VERSION_CODES.M)
    fun observeNetworkChanges() = callbackFlow {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, this@NetworkObserver)

        val currentNetwork = connectivityManager.activeNetwork
        if (currentNetwork != null) {
            trySend(connectivityManager.isCurrentlyConnected(currentNetwork))
        } else {
            trySend(false)
        }

        awaitClose { connectivityManager.unregisterNetworkCallback(this@NetworkObserver) }
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        trySend(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        trySend(false)
    }
}
package com.dfcruz.talkie.platform.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConnectivityInfoProviderImpl(context: Context) : ConnectivityInfoProvider {

    private val connectivityManager = context.getSystemService(ConnectivityManager::class.java)

    private val _networkState = MutableStateFlow(getNetworkState())
    override val networkState: StateFlow<NetworkState> = _networkState.asStateFlow()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _networkState.value = NetworkState.CONNECTED
        }

        override fun onLost(network: Network) {
            _networkState.value = getNetworkState()
        }
    }

    init {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            .build()

        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    override fun getNetworkState(): NetworkState = runCatching {
        with(connectivityManager) {
            val isConnected = getNetworkCapabilities(activeNetwork)?.run {
                hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            } ?: false
            if (isConnected) NetworkState.CONNECTED else NetworkState.DISCONNECTED
        }
    }.getOrElse { NetworkState.DISCONNECTED }

    override fun unregister() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}

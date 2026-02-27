package com.dfcruz.talkie.platform.network

import kotlinx.coroutines.flow.StateFlow

interface ConnectivityInfoProvider {
    val networkState: StateFlow<NetworkState>
    fun getNetworkState(): NetworkState
    fun unregister()
}

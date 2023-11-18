package app.photos_challenge.viewmodel_test

import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.mockk.every
import io.mockk.mockk


fun ConnectivityManager.setActiveNetworkInfoForTest(isConnected: Boolean) {
    val networkInfo = mockk<NetworkInfo>()
    every { networkInfo.isConnected } returns isConnected
    every { activeNetworkInfo } returns networkInfo
}


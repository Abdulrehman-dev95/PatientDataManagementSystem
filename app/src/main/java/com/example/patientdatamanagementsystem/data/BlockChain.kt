package com.example.patientdatamanagementsystem.data

import android.app.Application
import com.reown.android.Core
import com.reown.android.CoreClient
import com.reown.android.relay.ConnectionType
import com.reown.appkit.client.AppKit
import com.reown.appkit.client.Modal
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class BlockChain(application: Application) {

    private var walletConnect: Wallet? = null

    init {
        val connectionType = ConnectionType.AUTOMATIC
        val projectId = "8f8a16485301cd59643e6c64205e1336"
        val appMetadata = Core.Model.AppMetaData(
            name = "BlockCare",
            description = "A Patient Data Management System",
            url = "https://yourdomain.com",
            icons = listOf("https://raw.githubusercontent.com/google/material-design-icons/master/png/social/person/materialicons/48dp/1x/baseline_person_black_48dp.png"),
            redirect = "patientdatamanagementsystem://wallet-callback",
        )


        CoreClient.initialize(
            application = application,
            projectId = projectId,
            metaData = appMetadata,
            connectionType = connectionType,
            onError = {
                Timber.tag("CoreClint Error ").e(it.throwable)
            },
        )

        AppKit.initialize(
            init = Modal.Params.Init(CoreClient),
            onSuccess = {
                Timber.tag("AppKit Success").i(
                    "AppKit initialized successfully"
                )
            },
            onError = {
                Timber.tag("AppKit Error").e(it.throwable)
            }
        )

    }


    fun connectWallet(onConnected: (String) -> Unit, onError: (String) -> Unit) {
        val pairing = CoreClient.Pairing.getPairings().firstOrNull() ?: runBlocking {
            CoreClient.Pairing.create()
        }
        if (pairing == null) {
            onError("Failed to create WalletConnect pairing.")
            return
        }

        AppKit.connect(
            onSuccess = {
                walletConnect = Wallet(address = it)
                onConnected(it)
            },
            onError = {
                onError(it.toString())
            },
            connect = Modal.Params.Connect(

                pairing = pairing
            )


        )


    }

    fun disconnectWallet() {
        AppKit.disconnect(
            onSuccess = {
                walletConnect = null
                Timber.tag("Appkit success").i("Appkit is disconnect")
            }, onError = {
                Timber.tag("Appkit Error").e("Appkit is not disconnect")
            }
        )
    }

    fun getShortenedAddress(): String {
        return walletConnect?.address?.let {
            "${it.take(6)}...${it.takeLast(4)}"
        } ?: "Not Connected"
    }


}

data class Wallet(val address: String)
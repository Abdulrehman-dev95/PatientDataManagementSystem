package com.example.patientdatamanagementsystem.data

import android.util.Log
import io.metamask.androidsdk.EthereumFlow
import io.metamask.androidsdk.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BlockChain(private val ethereum: EthereumFlow) {


    suspend fun connectWallet(): kotlin.Result<String> {
        return when (val result = ethereum.connect()) {
            is Result.Success.Item -> {
                val walletAddress = result.value
                if (walletAddress.isNotBlank()) {
                    Log.d("MetaMask", "Connected to wallet")
                    kotlin.Result.success(
                        result.value
                    )
                } else {
                    Log.e("MetaMask", "Error connecting to wallet")
                    kotlin.Result.failure(exception = Exception("Error connecting to wallet"))
                }
            }

            is Result.Error -> {
                Log.e("MetaMask", "Error connecting to wallet ${result.error.message}")
                kotlin.Result.failure(exception = Exception(result.error.message))
            }

            else -> {
                Log.e("MetaMask", "Unknown error")
                kotlin.Result.failure(exception = Exception("Unknown error please try again"))
            }

        }
    }


    suspend fun disconnectWallet() = withContext(Dispatchers.IO) {
        ethereum.disconnect(true)
    }

//    suspend fun getAddress(): String {
//        val address = ethereum.getEthAccounts()
//
//    }
//
//    suspend fun getBalance(address: String): String = withContext(Dispatchers.IO) {
//
//    }
//
//    fun isConnected(): Boolean {
//        return true
//    }


}


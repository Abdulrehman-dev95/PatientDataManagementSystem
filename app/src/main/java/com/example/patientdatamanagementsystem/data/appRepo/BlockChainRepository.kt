package com.example.patientdatamanagementsystem.data.appRepo

import com.example.patientdatamanagementsystem.data.BlockChain

interface BlockChainRepository {

   suspend fun connectWallet(): Result<String>
  suspend  fun disconnectWallet()
    fun getShortenedAddress(): String
    fun isConnected(): Boolean


}

class BlockChainRepoImpl(private val blockChain: BlockChain) : BlockChainRepository {

    override suspend fun connectWallet(): Result<String> {
      return  blockChain.connectWallet()
    }

    override suspend fun disconnectWallet() {
        blockChain.disconnectWallet()
    }

    override fun isConnected(): Boolean {
      return true
    }

    override fun getShortenedAddress(): String {
       return "0x1111"
    }


}
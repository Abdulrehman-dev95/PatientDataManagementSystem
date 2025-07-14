package com.example.patientdatamanagementsystem.data

import android.content.Context
import com.example.patientdatamanagementsystem.data.appRepo.AppPreferencesRepo
import com.example.patientdatamanagementsystem.data.appRepo.AuthenticationRepository
import com.example.patientdatamanagementsystem.data.appRepo.AuthenticationRepositoryImpl
import com.example.patientdatamanagementsystem.data.appRepo.BlockChainRepoImpl
import com.example.patientdatamanagementsystem.data.appRepo.BlockChainRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.metamask.androidsdk.DappMetadata
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.EthereumFlow
import io.metamask.androidsdk.SDKOptions


interface AppContainer {
    val authenticationRepository: AuthenticationRepository
    val appPreferencesRepo: AppPreferencesRepo
    val blockChainRepository: BlockChainRepository
}

class AppContainerImp(private val context: Context) : AppContainer {


    private val ethereum: EthereumFlow = EthereumFlow(
        ethereum = Ethereum(
            context = context,
            dappMetadata = DappMetadata(
                "${context.applicationInfo.name}",
                url = "https://${context.applicationInfo.name}.com"
            ),
            sdkOptions = SDKOptions(
                infuraAPIKey = "1f3b4aecaa0348618d609faeef280771",
                readonlyRPCMap = mapOf(
                    "0x1" to "https://mainnet.infura.io/v3/1f3b4aecaa0348618d609faeef280771"
                )
            )
        )
    )


    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val firebaseService = FirebaseService(auth = auth, db = db)
    private val blockChain = BlockChain(ethereum = ethereum)

    override val authenticationRepository: AuthenticationRepository by lazy {
        AuthenticationRepositoryImpl(firebaseService = firebaseService)
    }
    override val appPreferencesRepo: AppPreferencesRepo by lazy {
        AppPreferencesRepo(context = context)

    }
    override val blockChainRepository: BlockChainRepository by lazy {
        BlockChainRepoImpl(blockChain = blockChain)
    }


}
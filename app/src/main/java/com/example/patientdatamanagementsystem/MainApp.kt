package com.example.patientdatamanagementsystem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.patientdatamanagementsystem.ui.BlockCareNavGraph


@Composable
fun MainApp(modifier: Modifier = Modifier) {
    Scaffold(

        modifier = modifier.fillMaxSize()

    ){
        BlockCareNavGraph(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )


    }

}
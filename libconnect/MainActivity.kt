package com.example.libconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.libconnect.ui.screens.HomeScreen
import com.example.libconnect.ui.theme.LibConnectTheme
import com.example.libconnect.ui.theme.LightSage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LibConnectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color    = LightSage
                ) {
                    HomeScreen()
                }
            }
        }
    }
}
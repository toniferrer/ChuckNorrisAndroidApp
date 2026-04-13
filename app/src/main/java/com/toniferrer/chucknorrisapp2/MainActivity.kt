package com.toniferrer.chucknorrisapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.toniferrer.chucknorrisapp2.navegacion.NavegacionApp
import com.toniferrer.chucknorrisapp2.ui.theme.ChuckNorrisApp2Theme

data class ChuckNorrisBroma(
    val id: String,
    val value: String,
    val categories: List<String> = emptyList()
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChuckNorrisApp2Theme {
                NavegacionApp()
            }
        }
    }
}
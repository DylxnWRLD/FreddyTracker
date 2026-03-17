package com.example.freddytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.freddytracker.interfaz.navegar.NavGraph
import com.example.freddytracker.ui.theme.FreddyTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FreddyTrackerTheme {
                Previewcita()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Previewcita() {
    FreddyTrackerTheme {
        NavGraph()
    }
}
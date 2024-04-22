package com.stu26172.store.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.stu26172.store.core.presentation.components.Navigation
import com.stu26172.store.ui.theme.StoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreTheme(dynamicColor = false, darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF7F7F9)
                ) {
                    Navigation()
                }
            }
        }
    }
}


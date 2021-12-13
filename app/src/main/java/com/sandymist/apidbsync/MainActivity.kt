package com.sandymist.apidbsync

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.sandymist.apidbsync.ui.theme.APIDBSyncTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: APIDBSyncViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APIDBSyncTheme {
                Surface(color = MaterialTheme.colors.background) {
                    App(viewModel)
                }
            }
        }
    }
}

@Composable
fun App(viewModel: APIDBSyncViewModel) {
    viewModel.getColors()
    val colors = viewModel.colors.collectAsState()

    LazyColumn {
        items(colors.value) { color ->
            Text(text = color)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    APIDBSyncTheme {
        //App()
    }
}
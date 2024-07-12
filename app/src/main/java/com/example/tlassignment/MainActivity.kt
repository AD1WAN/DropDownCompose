package com.example.tlassignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dagger.hilt.android.AndroidEntryPoint



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val inputDataList = remember {
                    mutableStateOf(
                        mutableListOf(
                            InputData("3D View", "", InputDataType.dropdown),
                            InputData("Front", "", InputDataType.dropdown),
                            InputData("Reception", "", InputDataType.dropdown)
                        )
                    )
                }

                val mediaUpload = remember {
                    MediaUpload(inputDataList.value) { status ->
                        Log.d("MediaUploadStatus", status)
                    }
                }

                MediaUploadScreen(mediaUpload)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}

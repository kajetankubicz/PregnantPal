package com.example.PregnantPal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.PregnantPal.screen.login.loginViewModel
import com.example.PregnantPal.ui.theme.PregnantPalTheme
import com.example.PregnantPal.screen.Navigation.Navigation

class MainActivity : ComponentActivity() {
    public var isLightTheme = mutableStateOf(true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val loginViewModel: loginViewModel = viewModel()

            PregnantPalTheme(isLightTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Navigation(loginViewModel = loginViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val mainActivity = MainActivity()
    PregnantPalTheme(mainActivity.isLightTheme){}
}
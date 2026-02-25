package ru.korobeynikov.livebeerapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.korobeynikov.livebeerapplication.presentation.AuthorizationScreen
import ru.korobeynikov.livebeerapplication.presentation.EnterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "authorization",
                    modifier = Modifier.weight(1f)
                ) {
                    composable("authorization") {
                        AuthorizationScreen(navController)
                    }
                    composable("enter") {
                        EnterScreen(navController)
                    }
                    composable("registration") {
                        RegistrationScreen(navController)
                    }
                }
            }
        }
    }
}
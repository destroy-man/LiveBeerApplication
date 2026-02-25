package ru.korobeynikov.livebeerapplication.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AuthorizationScreen(navController: NavController) {
    Column(modifier = Modifier.padding(start = 30.dp)) {
        Spacer(modifier = Modifier.weight(1f))
        val onNavigateToEnter = {
            navController.navigate("enter")
        }
        val onNavigateToRegistration = {
            navController.navigate("registration")
        }

        Text(
            "Программа лояльности для клиентов LiveBeer",
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(end = 19.dp)
        )

        Row(modifier = Modifier.padding(top = 16.dp)) {
            AuthorizationButton(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                text = "Вход",
                onClick = onNavigateToEnter
            )
            AuthorizationButton(
                modifier = Modifier
                    .padding(end = 29.dp)
                    .weight(1f),
                text = "Регистрация",
                onClick = onNavigateToRegistration
            )
        }

        Button(
            modifier = Modifier
                .padding(end = 29.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color(0xFFE4E4E4)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF08070C)
            ),
            onClick = onNavigateToEnter
        ) {
            Text("Войти без регистрации")
        }
    }
}

@Composable
fun AuthorizationButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFE100),
            contentColor = Color(0xFF08070C)
        ),
        onClick = onClick
    ) {
        Text(text)
    }
}
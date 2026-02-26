package ru.korobeynikov.livebeerapplication.presentation.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.korobeynikov.livebeerapplication.presentation.PhoneVisualTransformation

@Composable
fun RegistrationScreen(
    navController: NavController,
    registrationViewModel: RegistrationViewModel = viewModel(),
) {
    val onBack: () -> Unit = {
        navController.popBackStack()
    }

    val colorCyan = Color(0xFF007AFF)
    val colorGrey = Color(0xFF8E8E93)
    val colorYellow = Color(0xFFFFE100)

    Scaffold(topBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = colorCyan
                )
            }
            Text("Назад", color = colorCyan, modifier = Modifier.offset((-15).dp))
        }
    }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding() + 8.dp)) {
            Text(
                "Регистрация аккаунта",
                fontSize = 36.sp,
                modifier = Modifier.padding(start = 24.dp, end = 37.dp)
            )
            Text(
                "Заполните поля данных ниже",
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 24.dp, top = 8.dp),
                color = colorGrey
            )

            val phone by registrationViewModel.phone.collectAsState(TextFieldValue("+7 "))
            val minLength = 3
            val maxLength = 13
            Text(
                "Номер телефона",
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 24.dp, top = 24.dp),
                color = colorGrey
            )
            OutlinedTextField(
                phone,
                onValueChange = { newValue ->
                    registrationViewModel.changePhone(newValue, minLength, maxLength)
                },
                visualTransformation = PhoneVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(fontSize = 17.sp, color = colorGrey),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = colorGrey
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp)
            )

            val fullName by registrationViewModel.fullName.collectAsState("")
            Text(
                "Ваше имя",
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 24.dp, top = 8.dp),
                color = colorGrey
            )
            OutlinedTextField(
                fullName,
                onValueChange = { newValue ->
                    registrationViewModel.changeFullName(newValue)
                },
                textStyle = TextStyle(fontSize = 17.sp, color = Color.Black),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = colorGrey
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp)
            )

            val birthDate by registrationViewModel.birthDate.collectAsState("ДД.ММ.ГГ")
            Text(
                "Дата рождения",
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 24.dp, top = 8.dp),
                color = colorGrey
            )
            OutlinedTextField(
                birthDate,
                onValueChange = {},
                enabled = false,
                textStyle = TextStyle(fontSize = 17.sp, color = colorGrey),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = colorGrey
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp)
                    .composed {
                        clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {

                        }
                    }
            )

            val isAgree by registrationViewModel.isAgree.collectAsState(false)
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(start = 15.dp, top = 6.dp)
            ) {
                Checkbox(checked = isAgree, onCheckedChange = { newValue ->
                    registrationViewModel.changeIsAgree(newValue)
                })

                val linkString: AnnotatedString = remember {
                    buildAnnotatedString {

                        val styleText = SpanStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                        )

                        val styleLink = SpanStyle(
                            color = colorCyan,
                            fontSize = 14.sp,
                            textDecoration = TextDecoration.Underline
                        )

                        withStyle(
                            style = styleText
                        ) {
                            append("Я согласен с ")
                        }

                        withLink(LinkAnnotation.Url(url = "https://livebeerspb.ru/privacy/")) {
                            withStyle(
                                style = styleLink
                            ) {
                                append("условиями обработки персональных данных.")
                            }
                        }
                    }
                }
                Text(linkString)
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorYellow,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                    .fillMaxWidth(),
                onClick = {}
            ) {
                Text("Регистрация")
            }
        }
    }
}
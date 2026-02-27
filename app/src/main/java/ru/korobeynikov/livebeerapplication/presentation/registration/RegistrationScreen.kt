package ru.korobeynikov.livebeerapplication.presentation.registration

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.korobeynikov.livebeerapplication.presentation.PhoneVisualTransformation
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController,
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
) {
    val onBack: () -> Unit = {
        navController.popBackStack()
    }
    val onAuthorization: () -> Unit = {
        navController.navigate("authorization")
    }

    val colorCyan = Color(0xFF007AFF)
    val colorGrey = Color(0xFF8E8E93)
    val colorYellow = Color(0xFFFFE100)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(topBar = {
        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = colorCyan,
                    modifier = Modifier.offset((-15).dp)
                )
                Text("Назад", color = colorCyan, modifier = Modifier.offset((-15).dp))
            }
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
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            showBottomSheet = true
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
                onClick = {
                    registrationViewModel.registerUser(
                        onSuccess = {
                            onAuthorization()
                        },
                        onShowMessage = {
                            Toast.makeText(
                                context,
                                "Такой пользователь уже зарегистрирован!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            ) {
                Text("Регистрация")
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(sheetState = sheetState, onDismissRequest = {}) {
                val months = listOf(
                    "", "", "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
                    "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь", "", ""
                )
                val monthsState = rememberLazyListState()
                val selectedMonth by remember {
                    derivedStateOf { monthsState.firstVisibleItemIndex + 1 }
                }

                val years = (2000..LocalDate.now().year).map { it.toString() }
                    .toMutableList()
                    .apply {
                        add(0, "")
                        add(0, "")
                        add("")
                        add("")
                    }
                    .toList()
                val yearsState = rememberLazyListState()
                val selectedYear by remember { derivedStateOf { yearsState.firstVisibleItemIndex + 1 } }

                val yearMonth = YearMonth.of(selectedYear, selectedMonth)
                val days = (1..yearMonth.lengthOfMonth()).map { it.toString() }
                    .toMutableList()
                    .apply {
                        add(0, "")
                        add(0, "")
                        add("")
                        add("")
                    }
                    .toList()
                val daysState = rememberLazyListState()
                val selectedDay by remember { derivedStateOf { daysState.firstVisibleItemIndex + 1 } }

                CalendarActionsBar(
                    colorCyan = colorCyan,
                    onCancel = {
                        scope.launch {
                            showBottomSheet = false
                        }
                    },
                    onDone = {
                        registrationViewModel.changeBirthDate(
                            selectedDay,
                            selectedMonth,
                            selectedYear
                        )
                        showBottomSheet = false
                    }
                )

                Row {
                    Picker(
                        modifier = Modifier
                            .height(listHeight.dp)
                            .weight(0.3f),
                        listState = daysState,
                        list = days
                    )
                    Picker(
                        modifier = Modifier
                            .height(listHeight.dp)
                            .weight(0.4f),
                        listState = monthsState,
                        list = months
                    )
                    Picker(
                        modifier = Modifier
                            .height(listHeight.dp)
                            .weight(0.3f),
                        listState = yearsState,
                        list = years
                    )
                }
            }
        }
    }
}

@Composable
fun CalendarActionsBar(colorCyan: Color, onCancel: () -> Unit, onDone: () -> Unit) {
    Row {
        Text(
            "Отмена",
            color = colorCyan,
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            modifier = Modifier
                .weight(0.3f)
                .clickable(onClick = onCancel)
        )
        Text(
            "Выберите дату",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            "Готово",
            color = colorCyan,
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            modifier = Modifier
                .weight(0.3f)
                .clickable(onClick = onDone)
        )
    }
}

@Composable
fun Picker(modifier: Modifier, listState: LazyListState, list: List<String>) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Border(itemHeight = itemHeight.dp, color = Color.Black)
        LazyColumn(state = listState) {
            items(list.size) { index ->
                Box(
                    modifier = Modifier.fillParentMaxHeight(1f / countOfVisibleItemsInPicker),
                    contentAlignment = Alignment.Center
                ) {
                    Text(list[index], fontSize = 15.sp)
                }
            }
        }
    }
}

// Количество видимых элементов в столбце
internal const val countOfVisibleItemsInPicker = 5

// Высота одного элемента
internal const val itemHeight = 35f

// Высота списка
internal const val listHeight = countOfVisibleItemsInPicker * itemHeight

@Composable
internal fun Border(itemHeight: Dp, color: Color) {
    val width = 2.dp
    val strokeWidthPx = with(LocalDensity.current) { width.toPx() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .drawBehind {
                drawLine(
                    color = color,
                    strokeWidth = strokeWidthPx,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f)
                )

                drawLine(
                    color = color,
                    strokeWidth = strokeWidthPx,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            }
    ) {}
}
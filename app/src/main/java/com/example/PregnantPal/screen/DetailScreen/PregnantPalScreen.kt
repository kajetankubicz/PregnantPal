package com.example.PregnantPal.screen.DetailScreen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.PregnantPal.model.MaternalData
import com.example.pregnantpal.R
import com.example.PregnantPal.components.addButton
import com.example.PregnantPal.components.textInput
import com.google.gson.Gson
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PregnantPalScreen(
    navController: NavController,
    name: String? = "pregnantPal_screen"
){

    val context = LocalContext.current


    var expanded = remember {
        mutableStateOf(false)
    }

    val pregnancyTypes = listOf("Singleton",  "Twins")
    var singleton_or_twins = remember {
        mutableStateOf(pregnancyTypes[0])
    }
    var singleton_or_twins_index = remember{
        mutableStateOf(0)
    }

    var fetus_1 = remember{
        mutableStateOf("")
    }

    var fetus_2 = remember{
        mutableStateOf("")
    }

    var examinationDate = remember {
        mutableStateOf(value = "")
    }

    var dayOfBirth = remember {
        mutableStateOf(value = "")
    }

    var height = remember {
        mutableStateOf(value = "")
    }

    var weight = remember {
        mutableStateOf(value = "")
    }

    val racialOrigin = listOf("White", "Black","South Asian","East Easian","Mixed")
    var expandedRacial = remember {
        mutableStateOf(false)
    }
    var racial_origin = remember {
        mutableStateOf(racialOrigin[0])
    }
    var racial_origin_index = remember{
        mutableStateOf(0)
    }

    var smoking = remember {
        mutableStateOf(2L)
    }

    val previous_preeclampsia = remember {
        mutableStateOf(2L)
    }

    val conceptionList = listOf("Spontenous", "Ovulatio drugs","In vitro fertilization")
    var expandedConception = remember {
        mutableStateOf(false)
    }

    var conception_method = remember {
        mutableStateOf(conceptionList[0])
    }
    var conception_method_index = remember{
        mutableStateOf(0)
    }

    var ch_hipertension = remember {
        mutableStateOf(2L)
    }

    var diabetes_type_1 = remember {
        mutableStateOf(2L)
    }

    var diabetes_type_2 = remember {
        mutableStateOf(2L)
    }

    var SLE = remember {
        mutableStateOf(2L)
    }

    var APS = remember {
        mutableStateOf(2L)
    }

    var nulliparous = remember {
        mutableStateOf(2L)
    }

    var MAP = remember {
        mutableStateOf("")
    }

    var UTAPI = remember {
        mutableStateOf("")
    }

    var dateOfBiophysicalMeasurements = remember {
        mutableStateOf("")
    }

    var plgf = remember {
        mutableStateOf(2L)
    }

    var pappa = remember {
        mutableStateOf(2L)
    }

    val ga_age = 0.0
    val inter_pregancy_interval = 0L

    val last_pregnancy_pe = 0L
    val last_pregnancy_delivery_weeks = 0L
    val last_pregnancy_delivery_days =  0L


    //Superior column that have a background color, so the space behind top bar is filed with color
    Column(modifier = Modifier
        .fillMaxSize()
        .background(androidx.compose.material3.MaterialTheme.colorScheme.tertiary)) {
        //Secondary column that have a padding so as top bar is not sticky to the left and right edges
        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                },
                elevation = 5.dp,
                backgroundColor = (androidx.compose.material3.MaterialTheme.colorScheme.onTertiary),
                modifier = Modifier.clip(shape = RoundedCornerShape(15.dp))
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // #1 row of data - Pregnancy type and dating
                item {
                    Card(
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.padding(10.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        border = BorderStroke(width = 2.dp, color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                    ) {
                        Text(
                            text = "Basic Information",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 20.dp),
                            horizontalAlignment = Alignment.Start
                        ) {

                            //Pregnancy Type
                            ExposedDropdownMenuBox(
                                expanded = expanded.value,
                                onExpandedChange = {
                                    expanded.value = !expanded.value
                                },
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                TextField(
                                    value = pregnancyTypes[singleton_or_twins_index.value],
                                    onValueChange = {
                                        singleton_or_twins_index.value = pregnancyTypes.indexOf(it)
                                    },
                                    label = { Text(text = "Pregnancy Type", color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)},
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = expanded.value
                                        )
                                    },
                                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                                        textColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        trailingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedTrailingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                    readOnly = true,
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done,
                                        keyboardType = KeyboardType.Text
                                    )
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false }) {
                                    pregnancyTypes.forEachIndexed{index,selectionOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                singleton_or_twins_index.value = index
                                                expanded.value = false
                                            }) {
                                            Text(text = selectionOption)
                                        }
                                    }
                                }
                            }

                            //Fetal crown-rump length
                            textInput(
                                text = "${fetus_1.value}",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp),
                                onTextChange = {
                                        fetus_1.value = it
                                        fetus_2.value = it
                                },
                                keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                label = "Fetal crown-rump length [mm]",
                                textColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                            )



                            //Examination Date
                            textInput(
                                text = "${examinationDate.value}",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp),
                                onTextChange = {
                                    if (it.all { char ->
                                            char.isDigit() || char == '-'
                                        })
                                        examinationDate.value = it.take(10)
                                },
                                keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                label = "Examination date [dd-mm-yyyy]",
                                textColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                )
                        }
                    }
                }
                //Divider
                item {
                    Divider(
                        modifier = Modifier.padding(10.dp),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                // #2 row of data - Maternal characteristics
                item {
                    Card(
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.padding(10.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        border = BorderStroke(width = 2.dp, color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                    ) {
                        Text(
                            text = "Maternal characteristics",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 20.dp),
                            horizontalAlignment = Alignment.Start
                        ) {

                            //Date of birth
                            textInput(
                                text = "${dayOfBirth.value}",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp),
                                onTextChange = {
                                    if (it.all { char ->
                                            char.isDigit() || char == '-'
                                        })
                                        dayOfBirth.value = it.take(10)
                                },
                                keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                label = "Expected date of birth [dd-mm-yyyy]",
                                textColor = MaterialTheme.colorScheme.onTertiaryContainer
                            )

                            //Height
                            textInput(
                                text = "${height.value}",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp),
                                onTextChange = {
                                        height.value = it
                                },
                                keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                label = "Height [cm]",
                                textColor = MaterialTheme.colorScheme.onTertiaryContainer
                            )

                            //Weight
                            textInput(
                                text = "${weight.value}",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp),
                                onTextChange = {
                                        weight.value = it
                                },
                                keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                label = "Weight [kg]",
                                textColor = MaterialTheme.colorScheme.onTertiaryContainer
                            )

                            //Racial origin
                            ExposedDropdownMenuBox(
                                expanded = expandedRacial.value,
                                onExpandedChange = {
                                    expandedRacial.value = !expandedRacial.value
                                },
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                TextField(
                                    value = racialOrigin[racial_origin_index.value],
                                    onValueChange = {
                                        racial_origin_index.value = racialOrigin.indexOf(it)
                                    },
                                    label = { Text(text = "Racial origin", color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer) },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = expandedRacial.value
                                        )
                                    },
                                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                                        textColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        trailingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedTrailingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                    readOnly = true,
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done,
                                        keyboardType = KeyboardType.Text
                                    )
                                )
                                ExposedDropdownMenu(
                                    expanded = expandedRacial.value,
                                    onDismissRequest = { expandedRacial.value = false }) {
                                    racialOrigin.forEachIndexed {index, selectionOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                racial_origin_index.value = index
                                                expandedRacial.value = false
                                            }) {
                                            Text(text = selectionOption)
                                        }
                                    }
                                }
                            }

                            //Smoking during pregnancy
                            Text(text = "Have you smoked during pregnancy?", color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = smoking.value == 1L,
                                    onCheckedChange = {
                                        smoking.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = smoking.value == 2L,
                                    onCheckedChange = {
                                        smoking.value = if(it) 2 else 1
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                            //Mother of the patient had PE

                            Text(text = "Have your mather had PE?", color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = previous_preeclampsia.value == 1L,
                                    onCheckedChange = {
                                        previous_preeclampsia.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes", color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = previous_preeclampsia.value == 2L,
                                    onCheckedChange = {
                                        previous_preeclampsia.value = if(it) 2 else 1
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                                )
                                Text(text = "No", color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }


                            //Conception method
                            ExposedDropdownMenuBox(
                                expanded = expandedConception.value,
                                onExpandedChange = {
                                    expandedConception.value = !expandedConception.value
                                },
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                TextField(
                                    value = conceptionList[conception_method_index.value],
                                    onValueChange = {
                                        conception_method_index.value = conceptionList.indexOf(it)
                                    },
                                    label = { Text(text = "Conception method", color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer) },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = expandedConception.value
                                        )
                                    },
                                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                                        textColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        trailingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedTrailingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                    readOnly = true,
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done,
                                        keyboardType = KeyboardType.Text
                                    )
                                )
                                ExposedDropdownMenu(
                                    expanded = expandedConception.value,
                                    onDismissRequest = { expandedConception.value = false }) {
                                    conceptionList.forEachIndexed {index, selectionOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                conception_method_index.value = index
                                                expandedConception.value = false
                                            }) {
                                            Text(text = selectionOption)
                                        }
                                    }
                                }
                            }


                        }
                    }
                }
                //Divider
                item {
                    Divider(
                        modifier = Modifier.padding(10.dp),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                // #3 row of data - Medical history
                item {
                    Card(
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.padding(10.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        border = BorderStroke(width = 2.dp, color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                    ) {
                        Text(
                            text = "Medical history",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 40.dp),
                            horizontalAlignment = Alignment.Start
                        ) {

                            //Chronic hypertension
                            Text(text = "Do you have chronic hypertension?", color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                Checkbox(
                                    checked = ch_hipertension.value == 1L,
                                    onCheckedChange = {
                                        ch_hipertension.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = ch_hipertension.value == 2L,
                                    onCheckedChange = {
                                        ch_hipertension.value = if(it) 2 else 1
                                    },
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                            //Diabetes type I
                            Text(text = "Do you have diabetes type I?",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                Checkbox(
                                    checked = diabetes_type_1.value == 1L,
                                    onCheckedChange = {
                                        diabetes_type_1.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = diabetes_type_1.value == 2L,
                                    onCheckedChange = {
                                        diabetes_type_1.value = if(it) 2 else 1
                                    },
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                            //Diabetes type II
                            Text(text = "Do you have diabetes type II?",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                Checkbox(
                                    checked = diabetes_type_2.value == 1L,
                                    onCheckedChange = {
                                        diabetes_type_2.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = diabetes_type_2.value == 2L,
                                    onCheckedChange = {
                                        diabetes_type_2.value = if(it) 2 else 1
                                    },
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                            //Systemic lupus erythematosus
                            Text(text = "Do you have lupus erythematosus?",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                Checkbox(
                                    checked = SLE.value == 1L,
                                    onCheckedChange = {
                                        SLE.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = SLE.value == 2L,
                                    onCheckedChange = {
                                        SLE.value = if(it) 2 else 1
                                    },
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                            //Anti-phospholipid syndrome
                            Text(text = "Do you have anti-phospholipid syndrome?",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            ) {
                                Checkbox(
                                    checked = APS.value == 1L,
                                    onCheckedChange = {
                                        APS.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = APS.value == 2L,
                                    onCheckedChange = {
                                        APS.value = if(it) 2 else 1
                                    },
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                        }
                    }
                }
                //Divider
                item {
                    Divider(
                        modifier = Modifier.padding(10.dp),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                // #4 row of data - Obstetric history
                item {
                    Card(
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.padding(10.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        border = BorderStroke(width = 2.dp, color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                    ) {
                        Text(
                            text = "Obstetric history",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 40.dp),
                            horizontalAlignment = Alignment.Start
                        ) {

                            //Nulliparous or Parous
                            Text(text = "Did you have at lest one pregnancy in less than 24 weeks?",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(all = 20.dp)
                            ) {
                                Checkbox(
                                    checked = nulliparous.value == 1L,
                                    onCheckedChange = {
                                        nulliparous.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = nulliparous.value == 2L,
                                    onCheckedChange = {
                                        nulliparous.value = if(it) 2 else 1
                                    },
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                        }
                    }
                }
                //Divider
                item {
                    Divider(
                        modifier = Modifier.padding(10.dp),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                // #5 row of data - Biophysical measurements
                item{
                    Card(
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.padding(10.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        border = BorderStroke(width = 2.dp, color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                    ) {
                        Text(
                            text = "Biophysical measurements",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 20.dp),
                            horizontalAlignment = Alignment.Start
                        ) {

                            //Mean arterial pressure
                            textInput(
                                text = "${MAP.value}",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp),
                                onTextChange = {
                                    if (it.all { char ->
                                            char.isDigit() || char == '.'
                                        })
                                        MAP.value = it.take(5)
                                },
                                keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                label = "Mean arterial pressure [mm]",
                                textColor = MaterialTheme.colorScheme.onTertiaryContainer
                            )

                            //Mean uterine artery
                            textInput(
                                text = "${UTAPI.value}",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp),
                                onTextChange = {
                                    if (it.all { char ->
                                            char.isDigit() || char == '.'
                                        })
                                        UTAPI.value = it.take(5)
                                },
                                keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                label = "Mean uterine artery PI",
                                textColor = MaterialTheme.colorScheme.onTertiaryContainer
                            )

                            //Date of measurements
                            textInput(
                                text = "${dateOfBiophysicalMeasurements.value}",
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp),
                                onTextChange = {
                                    if (it.all { char ->
                                            char.isDigit() || char == '-'
                                        })
                                        dateOfBiophysicalMeasurements.value = it.take(10)
                                },
                                keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                label = "Date of last measurements [dd-mm-yyyy]",
                                textColor = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }
                //Divider
                item {
                    Divider(
                        modifier = Modifier.padding(10.dp),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                // #6 row of data - Biochemical measurements
                item{
                    Card(
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier.padding(10.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        border = BorderStroke(width = 2.dp, color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                    ) {
                        Text(
                            text = "Biochemical measurements",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 40.dp),
                            horizontalAlignment = Alignment.Start
                        ) {

                            //PLGF
                            Text(text = "Did you last measurements included PLGF serum?",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(all = 20.dp)
                            ) {
                                Checkbox(
                                    checked = plgf.value == 1L,
                                    onCheckedChange = {
                                        plgf.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = plgf.value == 2L,
                                    onCheckedChange = {
                                        plgf.value = if(it) 2 else 1
                                    },
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                            //PAPP-A
                            Text(text = "Did you last measurements included PAPP-A serum?",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(all = 20.dp)
                            ) {
                                Checkbox(
                                    checked = pappa.value ==1L ,
                                    onCheckedChange = {
                                        pappa.value = if(it) 1 else 2
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                )
                                Text(text = "Yes",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)

                                Checkbox(
                                    checked = pappa.value == 2L,
                                    onCheckedChange = {
                                        pappa.value = if(it) 2 else 1
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
                                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                                    ),
                                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                                )
                                Text(text = "No",color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
                            }

                        }
                    }
                }
                // Button for saving
                item {
                    addButton(
                        modifier = Modifier.padding(20.dp),
                        text = "Save data",
                        onClick = {
                            if(
                                singleton_or_twins.value.isNotEmpty() &&
                                weight.value.isNotEmpty() &&
                                height.value.isNotEmpty() &&
                                fetus_1.value.isNotEmpty() &&
                                examinationDate.value.isNotEmpty() &&
                                dayOfBirth.value.isNotEmpty() &&
                                weight.value.isNotEmpty() &&
                                racial_origin.value.isNotEmpty() &&
                                conception_method.value.isNotEmpty() &&
                                MAP.value.isNotEmpty() &&
                                dateOfBiophysicalMeasurements.value.isNotEmpty()
                            ){
                                saveDataToJson(
                                    context, data = MaternalData(
                                        singleton_or_twins = singleton_or_twins_index.value + 1L,
                                        fetus_1 = fetus_1.value.toLong(),
                                        fetus_2 = fetus_2.value.toLong(),
                                        examinationDate = LocalDate.parse(examinationDate.value, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toEpochDay().toLong(),
                                        dayOfBirth = LocalDate.parse(dayOfBirth.value, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toEpochDay().toLong(),
                                        height = height.value.toLong(),
                                        weight = weight.value.toLong(),
                                        racial_origin = racial_origin_index.value + 1L,
                                        smoking = smoking.value,
                                        previous_preeclampsia = previous_preeclampsia.value,
                                        conception_method =conception_method_index.value +1L,
                                        ch_hipertension = ch_hipertension.value,
                                        diabetes_type_1 = diabetes_type_1.value,
                                        diabetes_type_2 = diabetes_type_2.value,
                                        SLE = SLE .value,
                                        APS = APS.value,
                                        nulliparous = nulliparous.value,
                                        last_pregnancy_pe = last_pregnancy_pe,
                                        last_pregnancy_delivery_weeks = last_pregnancy_delivery_weeks,
                                        last_pregnancy_delivery_days =  last_pregnancy_delivery_days,
                                        MAP =  MAP.value.toFloat(),
                                        dateOfBiophysicalMeasurements =  LocalDate.parse(dateOfBiophysicalMeasurements.value, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toEpochDay().toLong(),
                                        plgf =  plgf.value,
                                        pappa = pappa.value,
                                        ga_age = ga_age.toFloat(),
                                        inter_pregancy_interval = inter_pregancy_interval,
                                        UTAPI = UTAPI.value.toFloat()
                                    ))
                            }else{
                                Toast.makeText(context, "Data not saved, complete all data", Toast.LENGTH_SHORT).show()
                            }

                        }
                    )
                }

            }

        }
    }

}

private fun saveDataToJson(context: Context, data: MaternalData) {
    val json = Gson().toJson(data)

    // Request write external storage permission
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        return
    }

    // Get the downloads folder path
    val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val filePath = "$folder/data.json"

    // Create the directory if it doesn't exist
    if (!folder.exists()) {
        folder.mkdir()
    }

    // Write data to the file
    try {
        File(filePath).writeText(json)
        Toast.makeText(context, "Data saved to file successfully!", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Error saving data to file!", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}


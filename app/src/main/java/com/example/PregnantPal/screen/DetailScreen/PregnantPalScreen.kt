package com.example.pregnantpal.screen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.pregnantpal.model.MaternalData
import com.example.pregnantpal.R
import com.example.pregnantpal.components.addButton
import com.example.pregnantpal.components.textInput
import com.google.gson.Gson
import java.io.File
import java.time.LocalDateTime


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PregnantPalScreen(
    navController: NavController,
    name: String? = "pregnantPal_screen"
){

    val context = LocalContext.current

    val pregnancyTypes = listOf("Singleton", "Twins")
    var expanded = remember {
        mutableStateOf(false)
    }

    var pregnancyTypeChosen = remember {
        mutableStateOf(pregnancyTypes[0])
    }

    var fetalCrownRumpLength = remember{
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

    var racialOriginChosen = remember {
        mutableStateOf(racialOrigin[0])
    }

    var smokeState = remember {
        mutableStateOf(false)
    }

    val motherPEState = remember {
        mutableStateOf(false)
    }

    val conceptionList = listOf("Spontenous", "Ovulatio drugs","In vitro fertilization")
    var expandedConception = remember {
        mutableStateOf(false)
    }

    var conceptionTypeChosen = remember {
        mutableStateOf(conceptionList[0])
    }

    var hypertensionState = remember {
        mutableStateOf(false)
    }

    var diabetesFirstState = remember {
        mutableStateOf(false)
    }

    var diabetesSecondState = remember {
        mutableStateOf(false)
    }

    var lupusState = remember {
        mutableStateOf(false)
    }

    var phospholipidState = remember {
        mutableStateOf(false)
    }

    var whenLastPregnancyState = remember {
        mutableStateOf(false)
    }

    var meanArterialPressure = remember {
        mutableStateOf("")
    }

    var meanUterineArtery = remember {
        mutableStateOf("")
    }

    var dateOfBiophysicalMeasurements = remember {
        mutableStateOf("")
    }

    var serumPLGFState = remember {
        mutableStateOf(false)
    }

    var serumPAPPAState = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(6.dp)) {

        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                    )
                }
            },
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.background,
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
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.padding(10.dp),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    border = BorderStroke(width = 2.dp, color = Color.LightGray)
                ) {
                    Text(
                        text = "Basic Information",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
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
                                value = pregnancyTypeChosen.value,
                                onValueChange = {
                                    pregnancyTypeChosen.value = it
                                },
                                label = { Text(text = "Pregnancy Type") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded.value
                                    )
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                readOnly = true,
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Text
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false }) {
                                pregnancyTypes.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        onClick = {
                                            pregnancyTypeChosen.value = selectionOption
                                            expanded.value = false
                                        }) {
                                        Text(text = selectionOption)
                                    }
                                }
                            }
                        }

                        //Fetal crown-rump length
                        textInput(
                            text = "${fetalCrownRumpLength.value}",
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                            onTextChange = {
                                if (it.all { char ->
                                        char.isDigit() || char == '-'
                                    })
                                    fetalCrownRumpLength.value = it.take(5)
                            },
                            keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            label = "Fetal crown-rump length [mm] (eg. 45-84)"
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
                            label = "Examination date [dd-mm-yyyy]"
                        )
                    }
                }
            }
            //Divider
            item {
                Divider(
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colors.background
                )
            }
            // #2 row of data - Maternal characteristics
            item {
                Card(
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.padding(10.dp),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    border = BorderStroke(width = 2.dp, color = Color.LightGray)
                ) {
                    Text(
                        text = "Maternal characteristics",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
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
                            label = "Expected date of birth [dd-mm-yyyy]"
                        )

                        //Height
                        textInput(
                            text = "${height.value}",
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                            onTextChange = {
                                if (it.all { char ->
                                        char.isDigit()
                                    })
                                    height.value = it.take(3)
                            },
                            keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            label = "Height [cm]"
                        )

                        //Weight
                        textInput(
                            text = "${weight.value}",
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                            onTextChange = {
                                if (it.all { char ->
                                        char.isDigit()
                                    })
                                    weight.value = it.take(3)
                            },
                            keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            label = "Weight [kg]"
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
                                value = racialOriginChosen.value,
                                onValueChange = {
                                    racialOriginChosen.value = it
                                },
                                label = { Text(text = "Racial origin") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expandedRacial.value
                                    )
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                readOnly = true,
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Text
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = expandedRacial.value,
                                onDismissRequest = { expandedRacial.value = false }) {
                                racialOrigin.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        onClick = {
                                            racialOriginChosen.value = selectionOption
                                            expandedRacial.value = false
                                        }) {
                                        Text(text = selectionOption)
                                    }
                                }
                            }
                        }

                        //Smoking during pregnancy

                        Text(text = "Have you smoked during pregnancy?")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = smokeState.value,
                                onCheckedChange = {
                                    smokeState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !smokeState.value,
                                onCheckedChange = {
                                    smokeState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                        //Mother of the patient had PE

                        Text(text = "Have your mather had PE?")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = motherPEState.value,
                                onCheckedChange = {
                                    motherPEState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !motherPEState.value,
                                onCheckedChange = {
                                    motherPEState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
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
                                value = conceptionTypeChosen.value,
                                onValueChange = {
                                    conceptionTypeChosen.value = it
                                },
                                label = { Text(text = "Conception method") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expandedConception.value
                                    )
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                readOnly = true,
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Text
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = expandedConception.value,
                                onDismissRequest = { expandedConception.value = false }) {
                                conceptionList.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        onClick = {
                                            conceptionTypeChosen.value = selectionOption
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
                    color = MaterialTheme.colors.background
                )
            }
            // #3 row of data - Medical history
            item {
                Card(
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.padding(10.dp),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    border = BorderStroke(width = 2.dp, color = Color.LightGray)
                ) {
                    Text(
                        text = "Medical history",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 40.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        //Chronic hypertension
                        Text(text = "Do you have chronic hypertension?")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                        ) {
                            Checkbox(
                                checked = hypertensionState.value,
                                onCheckedChange = {
                                    hypertensionState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !hypertensionState.value,
                                onCheckedChange = {
                                    hypertensionState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                        //Diabetes type I
                        Text(text = "Do you have diabetes type I?")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                        ) {
                            Checkbox(
                                checked = diabetesFirstState.value,
                                onCheckedChange = {
                                    diabetesFirstState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !diabetesFirstState.value,
                                onCheckedChange = {
                                    diabetesFirstState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                        //Diabetes type II
                        Text(text = "Do you have diabetes type II?")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                        ) {
                            Checkbox(
                                checked = diabetesSecondState.value,
                                onCheckedChange = {
                                    diabetesSecondState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !diabetesSecondState.value,
                                onCheckedChange = {
                                    diabetesSecondState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                        //Systemic lupus erythematosus
                        Text(text = "Do you have lupus erythematosus?")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                        ) {
                            Checkbox(
                                checked = lupusState.value,
                                onCheckedChange = {
                                    lupusState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !lupusState.value,
                                onCheckedChange = {
                                    lupusState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                        //Anti-phospholipid syndrome
                        Text(text = "Do you have anti-phospholipid syndrome?")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                        ) {
                            Checkbox(
                                checked = phospholipidState.value,
                                onCheckedChange = {
                                    phospholipidState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !phospholipidState.value,
                                onCheckedChange = {
                                    phospholipidState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                    }
                }
            }
            //Divider
            item {
                Divider(
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colors.background
                )
            }
            // #4 row of data - Obstetric history
            item {
                Card(
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.padding(10.dp),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    border = BorderStroke(width = 2.dp, color = Color.LightGray)
                ) {
                    Text(
                        text = "Obstetric history",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 40.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        //Nulliparous or Parous
                        Text(text = "Did you have at lest one pregnancy in less than 24 weeks?")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(all = 20.dp)
                        ) {
                            Checkbox(
                                checked = whenLastPregnancyState.value,
                                onCheckedChange = {
                                    whenLastPregnancyState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !whenLastPregnancyState.value,
                                onCheckedChange = {
                                    whenLastPregnancyState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                    }
                }
            }
            //Divider
            item {
                Divider(
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colors.background
                )
            }
            // #5 row of data - Biophysical measurements
            item{
                Card(
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.padding(10.dp),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    border = BorderStroke(width = 2.dp, color = Color.LightGray)
                ) {
                    Text(
                        text = "Biophysical measurements",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 20.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        //Mean arterial pressure
                        textInput(
                            text = "${meanArterialPressure.value}",
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                            onTextChange = {
                                if (it.all { char ->
                                        char.isDigit() || char == '.'
                                    })
                                    meanArterialPressure.value = it.take(5)
                            },
                            keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            label = "Mean arterial pressure [mm]"
                        )

                        //Mean uterine artery
                        textInput(
                            text = "${meanUterineArtery.value}",
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                            onTextChange = {
                                if (it.all { char ->
                                        char.isDigit() || char == '.'
                                    })
                                    meanUterineArtery.value = it.take(5)
                            },
                            keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            label = "Mean uterine artery PI"
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
                            label = "Date of last measurements [dd-mm-yyyy]"
                        )
                    }
                }
            }
            //Divider
            item {
                Divider(
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colors.background
                )
            }
            // #6 row of data - Biochemical measurements
            item{
                Card(
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier.padding(10.dp),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    border = BorderStroke(width = 2.dp, color = Color.LightGray)
                ) {
                    Text(
                        text = "Biochemical measurements",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 40.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        //PLGF
                        Text(text = "Did you last measurements included PLGF serum?")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(all = 20.dp)
                        ) {
                            Checkbox(
                                checked = serumPLGFState.value,
                                onCheckedChange = {
                                    serumPLGFState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !serumPLGFState.value,
                                onCheckedChange = {
                                    serumPLGFState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                        //PAPP-A
                        Text(text = "Did you last measurements included PAPP-A serum?")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(all = 20.dp)
                        ) {
                            Checkbox(
                                checked = serumPAPPAState.value,
                                onCheckedChange = {
                                    serumPAPPAState.value = it
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Yes")

                            Checkbox(
                                checked = !serumPAPPAState.value,
                                onCheckedChange = {
                                    serumPAPPAState.value = !it
                                },
                                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            )
                            Text(text = "No")
                        }

                    }
                }
            }
            // Button for saving
            item {
                addButton(

                    text = "Save data",
                    onClick = {
                        if(
                            pregnancyTypeChosen.value.isNotEmpty() &&
                            fetalCrownRumpLength.value.isNotEmpty() &&
                            examinationDate.value.isNotEmpty() &&
                            dayOfBirth.value.isNotEmpty() &&
                            height.value.isNotEmpty() &&
                            weight.value.isNotEmpty() &&
                            racialOriginChosen.value.isNotEmpty() &&
                            conceptionTypeChosen.value.isNotEmpty() &&
                            meanArterialPressure.value.isNotEmpty() &&
                            dateOfBiophysicalMeasurements.value.isNotEmpty()
                        ){
                            saveDataToJson(context, data =
                            MaternalData(
                                pregnancyTypeChosen = pregnancyTypeChosen.value,
                                fetalCrownRumpLength = fetalCrownRumpLength.value,
                                examinationDate = examinationDate.value,
                                dayOfBirth = dayOfBirth.value,
                                height = height.value,
                                weight = weight.value,
                                racialOriginChosen = racialOriginChosen.value,
                                smokeState = smokeState.value,
                                motherPEState = motherPEState.value,
                                conceptionTypeChosen = conceptionTypeChosen.value,
                                hypertensionState = hypertensionState.value,
                                diabetesFirstState = diabetesFirstState.value,
                                lupusState = lupusState .value,
                                phospholipidState = phospholipidState.value,
                                whenLastPregnancyState = whenLastPregnancyState.value,
                                meanArterialPressure =  meanArterialPressure.value,
                                dateOfBiophysicalMeasurements =  dateOfBiophysicalMeasurements.value,
                                serumPLGFState =  serumPLGFState.value,
                                serumPAPPAState = serumPAPPAState.value,
                                entryDate = LocalDateTime.now()
                            ))

                            Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Data not saved, complete all data", Toast.LENGTH_SHORT).show()
                        }
                    })
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
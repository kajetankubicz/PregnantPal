package com.example.pregnantpal.screen.AdminScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pregnantpal.R
import com.example.pregnantpal.screen.Navigation.Screens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

//Admin screen that takes NavController as a parameter to handle navigation between screens
@Composable
fun AdminScreen(
    navController: NavController
) {


    Scaffold(
        //Top bar includes a logo, app name, and dropdown menu that contains sign out button
        topBar = {

            //Mutable state that is used to determine whether dropdown menu is expanded or not
            var expanded = remember {
                mutableStateOf(false)
            }

            TopAppBar(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                backgroundColor = (MaterialTheme.colorScheme.onSurface),
                elevation = 5.dp,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier.size(48.dp),
                        painter = rememberAsyncImagePainter(R.drawable.splash_image),
                        contentDescription = "Logo",
                        tint= Color.Unspecified
                    )

                    Spacer(Modifier.width(22.dp))

                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium,
                    )

                    Box {
                        IconButton(
                            onClick = { expanded.value = true },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Icon(
                                Icons.Filled.Settings, contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.primaryContainer
                            )
                        }
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.secondaryContainer,
                                    RoundedCornerShape(0.dp)
                                )
                                .width(95.dp)
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    FirebaseAuth.getInstance().currentUser?.let {
                                        FirebaseAuth.getInstance().signOut()
                                        navController.navigate(route = Screens.SignInScreen.name)
                                    }
                                }
                            ) {
                                Text(
                                    text = "Sign out",
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }


                }
            }
        },
        backgroundColor = MaterialTheme.colorScheme.surface,
    ) {

        //Everything beneath top bar
        EmailList()

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailList() {
    val firestore = FirebaseFirestore.getInstance()
    val storage = Firebase.storage
    val context = LocalContext.current


    var emailList by remember { mutableStateOf(emptyList<String>()) }
    var singleton_or_twinsData by remember { mutableStateOf(emptyList<Long?>()) }
    var fetus_1Data by remember { mutableStateOf(emptyList<Long?>()) }
    var fetus_2Data by remember { mutableStateOf(emptyList<Long?>()) }
    var date_of_birthData by remember { mutableStateOf(emptyList<Long?>()) }
    var heightData by remember { mutableStateOf(emptyList<Long?>()) }
    var weightData by remember { mutableStateOf(emptyList<Long?>()) }
    var racial_originData by remember { mutableStateOf(emptyList<Long?>()) }
    var smokingData by remember { mutableStateOf(emptyList<Long?>()) }
    var previous_preeclampsiaData by remember { mutableStateOf(emptyList<Long?>()) }
    var conception_methodData by remember { mutableStateOf(emptyList<Long?>()) }
    var ch_hipertensionData by remember { mutableStateOf(emptyList<Long?>()) }
    var diabetes_type_1Data by remember { mutableStateOf(emptyList<Long?>()) }
    var diabetes_type_2Data by remember { mutableStateOf(emptyList<Long?>()) }
    var sleData by remember { mutableStateOf(emptyList<Long?>()) }
    var apsData by remember { mutableStateOf(emptyList<Long?>()) }
    var nulliparousData by remember { mutableStateOf(emptyList<Long?>()) }
    var mapData by remember { mutableStateOf(emptyList<Long?>()) }
    var utapiData by remember { mutableStateOf(emptyList<Long?>()) }
    var ga_ageData by remember { mutableStateOf(emptyList<Long?>()) }


    LaunchedEffect(true) {
        val collectionRef = firestore.collection("users")
        val emailData = mutableListOf<String>()

        withContext(Dispatchers.IO) {
            val querySnapshot = collectionRef.get().await()

            for (document in querySnapshot) {
                val email = document.getString("email")
                email?.let {
                    emailData.add(email)
                }
            }
        }

        emailList = emailData // Initialize with null values
        singleton_or_twinsData = List(emailData.size) { null }
        fetus_1Data = List(emailData.size) { null }
        fetus_2Data = List(emailData.size) { null }
        date_of_birthData = List(emailData.size) { null }
        heightData = List(emailData.size) { null }
        weightData = List(emailData.size) { null }
        racial_originData = List(emailData.size) { null }
        smokingData = List(emailData.size) { null }
        previous_preeclampsiaData = List(emailData.size) { null }
        conception_methodData = List(emailData.size) { null }
        ch_hipertensionData = List(emailData.size) { null }
        diabetes_type_1Data = List(emailData.size) { null }
        diabetes_type_2Data = List(emailData.size) { null }
        sleData = List(emailData.size) { null }
        apsData = List(emailData.size) { null }
        nulliparousData = List(emailData.size) { null }
        mapData = List(emailData.size) { null }
        utapiData = List(emailData.size) { null }
        ga_ageData = List(emailData.size) { null }
    }

    suspend fun fetchData(email: String, index: Int) {
        val storageRef = storage.reference.child("$email.json")
        val maxDownloadSizeBytes: Long = 5 * 1024 * 1024 // 5MB max download size

        val jsonExists = withContext(Dispatchers.IO) {
            storageRef.metadata.await().sizeBytes > 0
        }

        if (!jsonExists) {
            Toast.makeText(
                context,
                "JSON file does not exist for $email",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val bytes = withContext(Dispatchers.IO) {
            storageRef.getBytes(maxDownloadSizeBytes).await()
        }

        val json = bytes.decodeToString()
        val jsonObject = JSONObject(json)
        singleton_or_twinsData = singleton_or_twinsData.toMutableList().apply {
            set(index, jsonObject.optLong("singleton_or_twins"))
        }
        fetus_1Data = fetus_1Data.toMutableList().apply {
            set(index, jsonObject.optLong("fetus_1"))
        }
        fetus_2Data = fetus_2Data.toMutableList().apply {
            set(index, jsonObject.optLong("fetus_2"))
        }
        date_of_birthData = date_of_birthData.toMutableList().apply {
            set(index, jsonObject.optLong("date_of_birth"))
        }
        heightData = heightData.toMutableList().apply {
            set(index, jsonObject.optLong("height"))
        }
        weightData = weightData.toMutableList().apply {
            set(index, jsonObject.optLong("weight"))
        }
        racial_originData = racial_originData.toMutableList().apply {
            set(index, jsonObject.optLong("racial_origin"))
        }
        smokingData = smokingData.toMutableList().apply {
            set(index, jsonObject.optLong("smoking"))
        }
        previous_preeclampsiaData = previous_preeclampsiaData.toMutableList().apply {
            set(index, jsonObject.optLong("previous_preeclampsia"))
        }
        conception_methodData = conception_methodData.toMutableList().apply {
            set(index, jsonObject.optLong("conception_method"))
        }
        ch_hipertensionData = ch_hipertensionData.toMutableList().apply {
            set(index, jsonObject.optLong("ch_hipertension"))
        }
        diabetes_type_1Data = diabetes_type_1Data.toMutableList().apply {
            set(index, jsonObject.optLong("diabetes_type_1"))
        }
        diabetes_type_2Data = diabetes_type_2Data.toMutableList().apply {
            set(index, jsonObject.optLong("diabetes_type_2"))
        }
        sleData = sleData.toMutableList().apply {
            set(index, jsonObject.optLong("sle"))
        }
        apsData = apsData.toMutableList().apply {
            set(index, jsonObject.optLong("aps"))
        }
        nulliparousData = nulliparousData.toMutableList().apply {
            set(index, jsonObject.optLong("nulliparous"))
        }
        mapData = mapData.toMutableList().apply {
            set(index, jsonObject.optLong("map"))
        }
        utapiData = utapiData.toMutableList().apply {
            set(index, jsonObject.optLong("utapi"))
        }
        ga_ageData = ga_ageData.toMutableList().apply {
            set(index, jsonObject.optLong("ga_age"))
        }
    }

    fun saveData(index: Int) {
        val email = emailList[index]
        val data = JSONObject().apply {
            put("singleton_or_twins", singleton_or_twinsData[index])
            put("fetus_1", fetus_1Data[index])
            put("fetus_2", fetus_2Data[index])
            put("date_of_birth", date_of_birthData[index])
            put("height", heightData[index])
            put("weight", weightData[index])
            put("racial_origin", racial_originData[index])
            put("smoking", smokingData[index])
            put("previous_preeclampsia", previous_preeclampsiaData[index])
            put("conception_method", conception_methodData[index])
            put("ch_hipertension", ch_hipertensionData[index])
            put("diabetes_type_1", diabetes_type_1Data[index])
            put("diabetes_type_2", diabetes_type_2Data[index])
            put("sle", sleData[index])
            put("aps", apsData[index])
            put("nulliparous", nulliparousData[index])
            put("map", mapData[index])
            put("utapi", utapiData[index])
            put("ga_age", ga_ageData[index])
        }

        val jsonString = data.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val storageRef = storage.reference.child("$email.json")
            val uploadTask = storageRef.putBytes(jsonString.toByteArray())
            uploadTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Data saved successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Data saving failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(emailList) { index, userEmail ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    // Call the function to fetch APS data when the card is clicked
                    CoroutineScope(Dispatchers.Main).launch {
                        fetchData(userEmail, index)
                    }
                }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = userEmail,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val currentSingleton_or_twinsData = singleton_or_twinsData[index]
                    val currentFetus_1Data = fetus_1Data[index]
                    val currentFetus_2Data = fetus_2Data[index]
                    val currentDate_of_birthData = date_of_birthData[index]
                    val currentHeightData = heightData[index]
                    val currentWeightData = weightData[index]
                    val currentRacial_originData = racial_originData[index]
                    val currentSmokingData = smokingData[index]
                    val currentPrevious_preeclampsiaData = previous_preeclampsiaData[index]
                    val currentConception_methodData = conception_methodData[index]
                    val currentCh_hipertensionData = ch_hipertensionData[index]
                    val currentDiabetes_type_1Data = diabetes_type_1Data[index]
                    val currentDiabetes_type_2Data = diabetes_type_2Data[index]
                    val currentSleData = sleData[index]
                    val currentApsData = apsData[index]
                    val currentNulliparousData = nulliparousData[index]
                    val currentMapData = mapData[index]
                    val currentUtapiData = utapiData[index]
                    val currentGa_ageData = ga_ageData[index]
                    if (currentApsData != null) {
                        TextField(
                            value = currentSingleton_or_twinsData?.toString() ?: "",
                            onValueChange = { value ->
                                singleton_or_twinsData = singleton_or_twinsData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("singleton_or_twins") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentFetus_1Data?.toString() ?: "",
                            onValueChange = { value ->
                                fetus_1Data = fetus_1Data.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("fetus_1") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentFetus_2Data?.toString() ?: "",
                            onValueChange = { value ->
                                fetus_2Data = fetus_2Data.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("fetus_2") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentDate_of_birthData ?.toString() ?: "",
                            onValueChange = { value ->
                                date_of_birthData  = date_of_birthData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("date_of_birth") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentHeightData ?.toString() ?: "",
                            onValueChange = { value ->
                                heightData   = heightData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("height") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentWeightData ?.toString() ?: "",
                            onValueChange = { value ->
                                weightData    = weightData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("weight") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentRacial_originData ?.toString() ?: "",
                            onValueChange = { value ->
                                racial_originData     = racial_originData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("racial_origin") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentSmokingData ?.toString() ?: "",
                            onValueChange = { value ->
                                smokingData      = smokingData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("smoking") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentPrevious_preeclampsiaData ?.toString() ?: "",
                            onValueChange = { value ->
                                previous_preeclampsiaData = previous_preeclampsiaData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("previous_preeclampsia") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentConception_methodData ?.toString() ?: "",
                            onValueChange = { value ->
                                conception_methodData  = conception_methodData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("conception_method") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentCh_hipertensionData ?.toString() ?: "",
                            onValueChange = { value ->
                                ch_hipertensionData  = ch_hipertensionData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("ch_hipertension") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentDiabetes_type_1Data ?.toString() ?: "",
                            onValueChange = { value ->
                                diabetes_type_1Data  = diabetes_type_1Data.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("diabetes_type_1") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentDiabetes_type_2Data ?.toString() ?: "",
                            onValueChange = { value ->
                                diabetes_type_2Data  = diabetes_type_2Data.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("diabetes_type_2") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentSleData?.toString() ?: "",
                            onValueChange = { value ->
                                sleData = sleData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("sle") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentApsData?.toString() ?: "",
                            onValueChange = { value ->
                                apsData = apsData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("aps") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentNulliparousData?.toString() ?: "",
                            onValueChange = { value ->
                                nulliparousData = nulliparousData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("nulliparous") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentMapData?.toString() ?: "",
                            onValueChange = { value ->
                                mapData = mapData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("map") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentUtapiData?.toString() ?: "",
                            onValueChange = { value ->
                                utapiData = utapiData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("utapi") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = currentGa_ageData?.toString() ?: "",
                            onValueChange = { value ->
                                ga_ageData = ga_ageData.toMutableList().apply {
                                    set(index, value.toLongOrNull())
                                }
                            },
                            label = { Text("ga_age") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        Button(
                            onClick = {
                                saveData(index)
                            },
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(top = 8.dp)
                        ) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }

}



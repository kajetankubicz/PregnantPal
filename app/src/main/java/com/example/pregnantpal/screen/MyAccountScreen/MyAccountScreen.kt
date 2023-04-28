package com.example.pregnantpal.screen.MyAccountScreen

import android.preference.PreferenceManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pregnantpal.R
import com.example.pregnantpal.components.textInput
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

@Composable
fun MyAccountScreen(
    navController: NavController,
) {
    val context = LocalContext.current


    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val profilePictureUri = remember {
        mutableStateOf(prefs.getString("profile_picture", null)?.toUri())
    }
    val firestore = Firebase.firestore
    val storageRef = Firebase.storage.reference.child("photos/")
    val currentUser = Firebase.auth.currentUser
    val database = Firebase.database
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val userRef = database.getReference("users/$userId")
    var name = remember { mutableStateOf("") }
    var surname = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }




    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Retrieve the user's profile picture URL from Firestore
        val profilePictureUrl = remember { mutableStateOf<String?>(null) }

        LaunchedEffect(currentUser?.uid) {
            val userRef = firestore.collection("users").document(currentUser?.uid!!)
            val snapshot = userRef.get().await()
            profilePictureUrl.value = snapshot.getString("profilePictureUrl")
        }

        LaunchedEffect(userRef) {
            userRef.child("name").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val nameFromDatabase = snapshot.getValue<String>()
                    if (nameFromDatabase != null) {
                        name.value = nameFromDatabase
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    name.value = "There was an error with uploading your name"
                }
            })
            userRef.child("surname").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val surnameFromDatabase = snapshot.getValue<String>()
                    if (surnameFromDatabase != null) {
                        surname.value = surnameFromDatabase
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    surname.value = "There was an error with uploading your surname"
                }
            })
            userRef.child("email").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val emailFromDatabase = snapshot.getValue<String>()
                    if (emailFromDatabase != null) {
                        email.value = emailFromDatabase
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    email.value = "There was an error with uploading your email"
                }
            })
        }

        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
                uri?.let {
                    val profilePictureRef = storageRef.child("profile_pictures/${currentUser?.uid}")
                    profilePictureRef.putFile(uri).addOnSuccessListener { taskSnapshot ->
                        profilePictureRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                            // Save the download URL to Firestore
                            val userRef = firestore.collection("users").document(currentUser?.uid!!)
                            userRef.update("profilePictureUrl", downloadUrl.toString())

                            // Update the profilePictureUrl state variable
                            profilePictureUrl.value = downloadUrl.toString()
                        }
                    }
                    profilePictureUri.value = it
                    prefs.edit().putString("profile_picture", it.toString()).apply()
                }
            }


        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                TopAppBar(
                    title = {
                        androidx.compose.material.Text(
                            text = stringResource(id = R.string.app_name),
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow Back",
                                tint = MaterialTheme.colorScheme.primaryContainer
                            )
                        }
                    },
                    elevation = AppBarDefaults.TopAppBarElevation,
                    backgroundColor = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clip(shape = RoundedCornerShape(15.dp))
                )
                Spacer(modifier = Modifier.size(20.dp))

                val loading = profilePictureUrl.value == null


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { launcher.launch("image/*") }
                            .width(200.dp)
                            .size(200.dp)
                            .border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (loading) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Profile Picture",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        } else if (profilePictureUrl.value != null) {
                            BoxWithConstraints {
                                Image(
                                    painter = rememberImagePainter(data = profilePictureUrl.value),
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape),
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.Center,
                                )
                            }
                        }
                    }
                }


                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=16.dp),
                    text = "Tap the circle to select/change a profile picture",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    textInput(
                        text = name.value,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp),
                        onTextChange = {
                            name.value = it
                            userRef.child("name").setValue(it)
                        },
                        keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        label = "Name",
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unfocusedIndicator = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    textInput(
                        text = surname.value,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp),
                        onTextChange = {
                            surname.value = it
                            userRef.child("surname").setValue(it)
                        },
                        keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        label = "Surname",
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    textInput(
                        text = email.value,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp),
                        onTextChange = {
                            email.value = it
                            userRef.child("email").setValue(it)
                        },
                        keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        label = "Email",
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }


            }
        }
    }
}

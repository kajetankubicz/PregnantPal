package com.example.pregnantpal.screen.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.compose.material3.MaterialTheme

//Composable function that created login screen
//LoginViewModel menages state of login screen
//onNav... callback functions that are invoked when the used navigates to home, sign up or admin page
@Composable
fun loginScreen(
    loginViewModel: loginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToSignUpPage:() -> Unit,
    onNavToAdminPage:() -> Unit

) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "Login",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.primary
        )

        //First thing that app does
        //If the error exits than it is displayed
        if (isError){
            Text(
                text = loginUiState?.loginError ?: "unknown error",
                color = MaterialTheme.colorScheme.error
            )
        }

        //Text field to fill userName = email
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.userName ?: "",
            onValueChange = {loginViewModel?.onUsernameChange(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            label = {
                Text(
                    text = "Email",
                    color = MaterialTheme.colorScheme.onPrimaryContainer

                )
            },
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            )
        )

        //Text field to fill password
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.password ?: "",
            onValueChange = { loginViewModel?.onPasswordChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            label = {
                Text(
                    text = "Password",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            )
        )

        Button(
            onClick = { loginViewModel?.loginUser(context) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.inverseSurface,
                contentColor = MaterialTheme.colorScheme.inverseOnSurface
            )

        ) {
            Text(
                text = "Sign In"
            )
        }
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an Account?",
                color =  MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(
                onClick = { onNavToSignUpPage.invoke() },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = MaterialTheme.colorScheme.inverseSurface,
                    contentColor = MaterialTheme.colorScheme.inverseOnSurface
                )) {
                Text(text = "SignUp")
            }


        }

        //If user correctly filled email and password fields that for a short moment the Circular Progress Indicator is displayed on the screen
        if (loginUiState?.isLoading == true){
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimaryContainer)
        }

        //That functions performs background task to check whether  the user is an admin or a regular user and navigates to the appropriate  screen
        LaunchedEffect(key1 = loginViewModel?.hasUser){
            val userId = Firebase.auth.currentUser?.uid
            if(loginViewModel?.hasUser == true) {
                if (userId != null) {
                    val usersCollection = Firebase.firestore.collection("users")
                    usersCollection.document(userId).get()
                        .addOnSuccessListener { userDoc ->
                            val isAdmin = userDoc.getBoolean("isAdmin") ?: false
                            if (isAdmin) {
                                Log.d("MyApp", "Admin")
                                onNavToAdminPage.invoke()
                            } else {
                                Log.d("MyApp", "User!")
                                onNavToHomePage.invoke()
                            }
                        }
                }
            }
        }

    }
}

@Composable
fun SignUpScreen(
    loginViewModel: loginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToLoginPage:() -> Unit
) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.primary
        )

        if (isError){
            Text(
                text = loginUiState?.signUpError ?: "unknown error",
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.userNameSignUp ?: "",
            onValueChange = {loginViewModel?.onUsernameSignUpChange(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            label = {
                Text(
                    text = "Email",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.passwordSignUp ?: "",
            onValueChange = { loginViewModel?.onPasswordSignUpChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            label = {
                Text(
                    text = "Password",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor =MaterialTheme.colorScheme.onPrimaryContainer,
                focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.confirmedPasswordSingUp ?: "",
            onValueChange = { loginViewModel?.onConfirmedPasswordChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            label = {
                Text(
                    text = "Confirm Password",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            )
        )

        Button(
            onClick = { loginViewModel?.createUser(context) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.inverseSurface,
                contentColor = androidx.compose.material3.MaterialTheme.colorScheme.inverseOnSurface
            )) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an Account?",
                color =  MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(
                onClick = { onNavToLoginPage.invoke() },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = MaterialTheme.colorScheme.inverseSurface,
                    contentColor = MaterialTheme.colorScheme.inverseOnSurface
                )) {
                Text(text = "Sign In")
            }
        }

        if (loginUiState?.isLoading == true){
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimaryContainer)
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser){
            if (loginViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }
    }
}



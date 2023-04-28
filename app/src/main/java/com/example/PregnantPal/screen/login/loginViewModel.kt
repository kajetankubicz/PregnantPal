package com.example.pregnantpal.screen.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pregnantpal.repository.AuthRepository
import kotlinx.coroutines.launch

class loginViewModel(
    private val repository: AuthRepository = AuthRepository(),
) :ViewModel() {

    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUsernameChange(userName: String){
        loginUiState = loginUiState.copy(userName = userName)
    }

    fun onPasswordChange(password: String){
        loginUiState = loginUiState.copy(password = password)
    }

    fun onUsernameSignUpChange(userName: String){
        loginUiState = loginUiState.copy(userNameSignUp = userName)
    }

    fun onPasswordSignUpChange(password: String){
        loginUiState = loginUiState.copy(passwordSignUp = password)
    }

    fun onConfirmedPasswordChange(password: String){
        loginUiState = loginUiState.copy(confirmedPasswordSingUp = password)
    }

    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() && loginUiState.password.isNotBlank()

    private fun validateSignUpForm() =
        loginUiState.userNameSignUp.isNotBlank() && loginUiState.passwordSignUp.isNotBlank() && loginUiState.confirmedPasswordSingUp.isNotBlank()


    //Function that creates user using provided information form the loginUiState
    fun createUser(context: Context) = viewModelScope.launch {
        try {
            //Check sign-up form
            if( !validateSignUpForm() ){
                throw java.lang.IllegalArgumentException("email and password can not be empty")
            }
            //Set loginUiState isLoading flag to true before calling te repository
            loginUiState = loginUiState.copy(isLoading = true)
            if(loginUiState.passwordSignUp != loginUiState.confirmedPasswordSingUp){
                throw IllegalArgumentException("Passwords do not match")
            }
            loginUiState = loginUiState.copy(signUpError = null)
            //Update the loginUiState
            repository.createUser(
                loginUiState.userNameSignUp,
                loginUiState.passwordSignUp
            ){ isSuccessful ->
                //If login is successful that loginUiState isSuccessLogin flag is set to true and on the screen Toast message is shown
                if(isSuccessful){
                    Toast.makeText(
                        context,
                        "Success login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }
                //If login is unsuccessful that loginUiState isSuccessLogin flag is set to false and on the screen Toast message is shown
                else{
                    Toast.makeText(
                        context,
                        "Unsuccessful login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
            //If the exception is caught during the process of login it updates the loginUiState signUpError flag
        }catch (e: Exception){
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if( !validateLoginForm() ){
                throw java.lang.IllegalArgumentException("email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.userName,
                loginUiState.password,
            ){ isSuccessful ->
                if(isSuccessful){
                    Toast.makeText(
                        context,
                        "Success login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Unsuccessful login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        }catch (e: Exception){
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }
}

//Data class that defines the state of the login screen
data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val userNameSignUp: String = "",
    val passwordSignUp: String = "",
    val confirmedPasswordSingUp: String = "",
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null,
    val isAdmin: Boolean = false
)
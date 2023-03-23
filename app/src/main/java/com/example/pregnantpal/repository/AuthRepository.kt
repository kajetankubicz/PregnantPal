package com.example.pregnantpal.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {
    val currentUser: FirebaseUser? = Firebase.auth.currentUser
    fun hasUser():Boolean = Firebase.auth.currentUser != null
    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    suspend fun createUser(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit,
    ) = withContext(Dispatchers.IO) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Add user data to Firestore collection
                    val userId = Firebase.auth.currentUser?.uid.orEmpty()
                    val userData = hashMapOf(
                        "email" to email,
                        "isAdmin" to false
                    )
                    Firebase.firestore.collection("users").document(userId)
                        .set(userData)
                        .addOnSuccessListener {
                            onComplete.invoke(true)
                        }
                        .addOnFailureListener {
                            onComplete.invoke(false)
                        }
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }

    suspend fun login(
        email: String,
        password: String,
        onComplete:(Boolean)-> Unit
    ) = withContext(Dispatchers.IO){
        Firebase.auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }

}


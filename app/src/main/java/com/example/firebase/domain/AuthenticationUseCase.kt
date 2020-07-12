package com.example.firebase.domain

import androidx.lifecycle.MutableLiveData
import com.example.firebase.data.AuthenticationService
import com.example.firebase.ui.model.dao.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.auth.User

class AuthenticationUseCase {

    val authenticationService = AuthenticationService()

    fun firebaseCreateNewUserWithEmailAndPassword(email:String, pass:String, block: (user: Usuario) -> Unit) {
        return authenticationService.createUserWithEmailAndPassword(email, pass, block)
    }

    fun getAuthenticatedUser() : String {
        return authenticationService.getAuthenticatedUser()
    }

    fun signOut() : String{
        return authenticationService.signOut()
    }

}
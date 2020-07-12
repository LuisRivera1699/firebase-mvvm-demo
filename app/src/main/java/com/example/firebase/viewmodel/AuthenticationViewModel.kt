package com.example.firebase.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.domain.AuthenticationUseCase
import com.example.firebase.ui.model.dao.Usuario
import com.google.android.gms.common.api.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch


class AuthenticationViewModel : ViewModel() {

    val authenticationUseCase = AuthenticationUseCase()
    private var userData = MutableLiveData<Usuario>()
    private val authenticatedMail = MutableLiveData<String>()

    private val out = MutableLiveData<String>()

    fun setUserData(user: Usuario) {
        userData.value = user
    }

    fun setAuthenticatedMail(mail: String) {
        authenticatedMail.value = mail
    }

    fun setOut(signedOut: String) {
        out.value = signedOut
    }

    fun signUpNewUser(mail: String, pass: String) {
        authenticationUseCase.firebaseCreateNewUserWithEmailAndPassword(mail, pass) {
            setUserData(it)
            Log.d("register::", it.toString())
        }
    }

    fun getAuthenticatedMail() {
        setAuthenticatedMail(authenticationUseCase.getAuthenticatedUser())
    }

    fun signOut() {
        setOut(authenticationUseCase.signOut())
    }

    fun getSignUpNewUserLiveData(): LiveData<Usuario> {
        return userData
    }

    fun getAuthenticatedUserLiveData(): LiveData<String> {
        return authenticatedMail
    }

    fun signOutLiveData(): LiveData<String> {
        return out
    }

}
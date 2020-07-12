package com.example.firebase.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebase.ui.model.dao.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error

class AuthenticationService {

    private val mauth = FirebaseAuth.getInstance()
    private val user = Usuario()

    fun getAuthenticatedUser(): String {
        val firebaseUser = mauth.currentUser
        if (firebaseUser != null) {
            return firebaseUser.email!!
        } else {
            return "Not signed"
        }
    }

    fun createUserWithEmailAndPassword(
        mail: String,
        pass: String,
        block: (user: Usuario) -> Unit
    ) {
        mauth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                val firebaseUser = mauth.currentUser
                if (firebaseUser != null) {
                    user.uid = firebaseUser.uid
                    user.email = firebaseUser.email
                    user.isAuthenticated = true
                    block(user)
                } else {
                    Log.d("Signup::", "Ha ocurrido un error")
                }
            } else {
                Log.d("Signup::", "Ha ocurrido un error")
            }
        }
    }

    fun signOut(): String {
        try {
            mauth.signOut()
            return "Out"
        } catch (error: Error) {
            Log.d("ExceptionSignOut:", error.message!!)
            return "Nothing"
        }
    }


}
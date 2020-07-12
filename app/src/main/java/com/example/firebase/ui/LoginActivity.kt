package com.example.firebase.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.R
import com.example.firebase.ui.fragments.LoginFragment
import com.example.firebase.ui.fragments.RegisterFragment
import com.example.firebase.ui.interfaces.OnFragmentChange
import com.example.firebase.ui.interfaces.OnRegister
import com.example.firebase.ui.model.dao.Usuario
import com.example.firebase.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), OnFragmentChange, OnRegister {

    private var fraLogin : Fragment? = null
    private var fraRegister : Fragment? = null


    private var viewModel : AuthenticationViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpViewModel()

        fraLogin = LoginFragment()
        fraRegister = RegisterFragment()

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaAuthentication, fraLogin!!)
        ft.commit()

        viewModel!!.getAuthenticatedMail()
    }

    fun setUpViewModel(){
        viewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        val authenticatioObserver = Observer<Usuario> {
            if (it.isAuthenticated){
                goToMainActivity()
            }else{
                Log.d("AuthenticationViewM:", it.toString())
            }
        }

        val emailAuthenticationObserver = Observer<String> {

            if(it == "Not signed"){
                // Quedarse y no hacer nada
            } else{
                goToMainActivity()
            }

            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel?.getSignUpNewUserLiveData()?.observe(this, authenticatioObserver)
        viewModel?.getAuthenticatedUserLiveData()?.observe(this,emailAuthenticationObserver)

    }

    private fun goToMainActivity() {
        val intent = Intent()
        intent.setClass(this, MainActivity::class.java)

        startActivityForResult(intent, 1000)
        this.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1000){
            Log.d("Logout:", "Logged out.")
            if (resultCode == Activity.RESULT_OK){
                Toast.makeText(this, "Out", Toast.LENGTH_LONG).show()
            }
        }
    }

    /*fun createUserWithEmailAndPassword(email: String, password: String) {
        auth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Log.d("register:", "createUserWithEmail:success")
                    Toast.makeText(
                        this,
                        auth!!.currentUser!!.email!!,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Log.d("register:", "createUserWithEmail:failed")
                    Toast.makeText(
                        this,
                        "No se ha podido crear",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }*/

    override fun cambiarFragment(fraName: String) {
        if(fraName=="login"){
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.flaAuthentication, fraLogin!!)
            ft.commit()
        }
        if(fraName=="signup"){
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.flaAuthentication, fraRegister!!)
            ft.commit()
        }
    }

    override fun register(email: String, pass: String) {
        viewModel!!.signUpNewUser(email, pass)
    }
}
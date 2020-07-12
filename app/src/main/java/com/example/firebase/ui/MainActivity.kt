package com.example.firebase.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.R
import com.example.firebase.ui.model.dao.TipoPokemon
import com.example.firebase.ui.model.dao.TipoPokemonDAO
import com.example.firebase.viewmodel.AuthenticationViewModel

class MainActivity : AppCompatActivity() {

    var butLogOut : Button? = null

    var viewModel : AuthenticationViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()

        butLogOut = findViewById(R.id.butLogOut)

        val daoTipoPokemon = TipoPokemonDAO()
        /*daoTipoPokemon.crearTipo(TipoPokemon("", "Nuevo tipo"), {
            Log.i(javaClass.canonicalName, "Guardado Correcto")
        }, {
            Log.e(javaClass.canonicalName, it)
        })*/

        daoTipoPokemon.modificarTipo(TipoPokemon("DqR7y8FJcw6SYnn6wBVM", "PLANTA"), {
            Log.i(javaClass.canonicalName, "Modificado Correcto")
        }, {
            Log.e(javaClass.canonicalName, it)
        })

        butLogOut?.setOnClickListener {
            viewModel?.signOut()
        }

        /*daoTipoPokemon.obtenerTipo { lista : List<TipoPokemon> ->

        }*/
    }

    fun setViewModel(){
        viewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        val signedOutObserver = Observer<String> {
            if(it == "Out"){
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Log.d("LogOut::", " Ha ocurrido un error")
            }
        }

        viewModel?.signOutLiveData()?.observe(this, signedOutObserver)
    }
}

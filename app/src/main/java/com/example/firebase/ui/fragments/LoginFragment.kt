package com.example.firebase.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.firebase.R
import com.example.firebase.ui.interfaces.OnFragmentChange

class LoginFragment: Fragment() {

    private var listener : OnFragmentChange? = null
    private var butQuieroRegistrar : Button? = null

    private var eteMailLogin : EditText? = null
    private var etePassLogin : EditText? = null
    private var butLogin : Button? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as OnFragmentChange
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        butQuieroRegistrar = view?.findViewById(R.id.butQuieroRegistrar)

        eteMailLogin = view?.findViewById(R.id.eteMailLogin)
        etePassLogin = view?.findViewById(R.id.etePasswordLogin)

        butLogin = view?.findViewById(R.id.butIngresar)

        butQuieroRegistrar?.setOnClickListener {
            listener?.cambiarFragment("signup")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

}
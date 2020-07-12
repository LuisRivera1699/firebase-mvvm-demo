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
import com.example.firebase.ui.interfaces.OnRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class RegisterFragment : Fragment() {

    private var listener: OnFragmentChange? = null
    private var registerListener: OnRegister? = null

    private var butQuieroLogear: Button? = null

    private var eteMailRegistrar: EditText? = null
    private var etePassRegistrar: EditText? = null

    private var butRegistrar: Button? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as OnFragmentChange
        registerListener = activity as OnRegister
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        butQuieroLogear = view?.findViewById(R.id.butQuieroLogear)

        eteMailRegistrar = view?.findViewById(R.id.eteMailRegistro)
        etePassRegistrar = view?.findViewById(R.id.etePasswordRegistro)

        butRegistrar = view?.findViewById(R.id.butRegistrar)

        butQuieroLogear?.setOnClickListener {
            listener?.cambiarFragment("login")
        }

        butRegistrar?.setOnClickListener {

            registerListener?.register(
                eteMailRegistrar?.text.toString(),
                etePassRegistrar?.text.toString()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

}
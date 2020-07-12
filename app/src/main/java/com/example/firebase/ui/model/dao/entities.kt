package com.example.firebase.ui.model.dao

import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class TipoPokemon(val id: String, val nombre: String)

data class Usuario(
    var uid: String? = null,
    var email: String? = null,
    var isAuthenticated: Boolean = false,
    var isNew: Boolean = false,
    var isCreated: Boolean = false
)

class Usuarioo(var uid: String, var email: String) : Serializable {
    var isAuthenticated: Boolean? = null
    var isNew: Boolean? = null
    var isCreated: Boolean? = null
}
package com.example.firebase.ui.model.dao

import com.google.firebase.firestore.FirebaseFirestore

class TipoPokemonDAO {
    fun obtenerTipo(block : (List<TipoPokemon>) -> Unit){
        val databaseReference = FirebaseFirestore.getInstance()

        val listaTipoPokemones = ArrayList<TipoPokemon>()

        val collection = databaseReference?.collection("tipos")
        collection?.get()?.addOnSuccessListener {
            for (doc in it){
                listaTipoPokemones.add(TipoPokemon(doc.id, doc["nombre"] as String))
            }
            /*it.forEach {
                listaTipoPokemones.add(TipoPokemon(it.id, it["nombre"] as String))
            }*/
            block(listaTipoPokemones)
        }
    }

    fun crearTipo(tipo: TipoPokemon, block : () -> Unit, blockError : (error: String) -> Unit){
        val databaseReference = FirebaseFirestore.getInstance()
        val collection = databaseReference.collection("tipos")
        val map = HashMap<String, Any>()
        map.put("nombre", tipo.nombre)

        collection.add(map).addOnSuccessListener { documentReference ->
            block()
        }.addOnFailureListener{ ex : Exception ->
            blockError(ex.message!!)
        }
    }

    fun modificarTipo(tipo : TipoPokemon, block : () -> Unit, blockError : (error: String) -> Unit) {
        val databaseReference = FirebaseFirestore.getInstance()
        val document = databaseReference.collection("tipos").document(tipo.id)

        val map = HashMap<String, Any>()
        map.put("nombre", tipo.nombre)

        document.update(map).addOnSuccessListener {
            block()
        }.addOnFailureListener {
            blockError(it.message!!)
        }
    }
}
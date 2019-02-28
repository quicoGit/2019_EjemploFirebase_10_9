package com.plumbaria.e_10_9_ejemplofirebase

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val database = FirebaseDatabase.getInstance()
        // myRef  --> vamos a buscar por la clave --> "mensaje"
        val myRef = database.getReference("mensaje")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Aqui escribira el valor asociado
                val value = dataSnapshot.getValue<String>(String::class.java!!)
                Log.d("Ejemplo Firebase", "Valor: " + value!!)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Ejemplo Firebase", "Error al leer.",
                        error.toException())
            }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Escribiendo en la base de datos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val database = FirebaseDatabase.getInstance()
            // myRef  --> "mensaje" sera la clave
            val myRef = database.getReference("mensaje")
            // myRef  --> "¡Hola, People!" es el valor
            myRef.setValue("¡Hola, People!")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}

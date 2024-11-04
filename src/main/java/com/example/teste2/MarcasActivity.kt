package com.example.teste2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MarcasActivity : AppCompatActivity() {

    private lateinit var marcasLista: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_marcas)

        val lista = listOf<String>("vult.png", "marca2")

        marcasLista = findViewById(R.id.imagemMarca)
        marcasLista.adapter = MarcasAdapter(lista)
        marcasLista.layoutManager
    }
}
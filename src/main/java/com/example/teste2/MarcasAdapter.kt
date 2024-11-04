package com.example.teste2

import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class MarcasAdapter(
    private val lista:List<String>
): Adapter<MarcasAdapter.marcasViewHolder>()  {



    inner class marcasViewHolder(val marcaItem: View):ViewHolder(marcaItem){
        val imagem: ImageView = marcaItem.findViewById(R.id.imagemMarca)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): marcasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_marcas, parent, false)
        val holder = marcasViewHolder(itemView)
        return holder
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(marcasViewHolder: marcasViewHolder, position: Int) {
        val imagem = lista[position]
        val imageId = marcasViewHolder.itemView.context.resources.getIdentifier(imagem, "drawable", marcasViewHolder.itemView.context.packageName)
        marcasViewHolder.imagem.setImageResource(imageId)
    }
}
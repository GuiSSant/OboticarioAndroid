package com.example.teste2

import android.graphics.Matrix
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class BannerAdapter(
    private val lista:List<String>
): Adapter<BannerAdapter.bannerViewHolder>()  {



    inner class bannerViewHolder(val bannerItem: View): ViewHolder(bannerItem){
        val imagem: ImageView = bannerItem.findViewById(R.id.imagemBanner)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bannerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_banner, parent, false)
        val holder = bannerViewHolder(itemView)
        return holder
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(bannerViewHolder: bannerViewHolder, position: Int) {
        val imagem = lista[position]
        val imageId = bannerViewHolder.itemView.context.resources.getIdentifier(imagem, "drawable", bannerViewHolder.itemView.context.packageName)
        bannerViewHolder.imagem.setImageResource(imageId)
        val matrix = Matrix()

        val scale =  bannerViewHolder.itemView.measuredWidth.toFloat()/ bannerViewHolder.imagem.drawable.intrinsicWidth

        matrix.setScale(0.22f, 0.22f)
        bannerViewHolder.imagem.imageMatrix = matrix
    }
}
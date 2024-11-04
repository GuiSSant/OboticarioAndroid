package com.example.teste2

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.OnScrollChangeListener
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager

class MainActivity : AppCompatActivity() {







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val scrollConteudo = findViewById<ScrollView>(R.id.scrollConteudo)
        val header = findViewById<ConstraintLayout>(R.id.fundoHeader)
        val conteudoHeader = findViewById<ConstraintLayout>(R.id.conteudo_header)
        val marcas = findViewById<RecyclerView>(R.id.recyclerMarcas)
        var alturaFinal: Int = 0
        var alturaInicial: Int = 0

        marcas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        marcas.setHasFixedSize(true)

        val lista = listOf<String>("vult", "nina", "siage", "pampers", "australian_gold", "dr_jones", "bio_oil2")

        val adapterMarcas = MarcasAdapter(lista)

        marcas.adapter = adapterMarcas



        header.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                alturaInicial = header.measuredHeight
                alturaFinal = header.measuredHeight - conteudoHeader.measuredHeight
                scrollConteudo.updatePadding(top = alturaInicial)
                header.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }})



        scrollConteudo.setOnScrollChangeListener { v: View, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            if(conteudoHeader.visibility == View.GONE){
                alturaFinal = header.measuredHeight
            }

            if (scrollY in 100..400) {
                if (conteudoHeader.visibility == VISIBLE){
                ValueAnimator.ofInt(alturaInicial, alturaFinal).apply {
                    addUpdateListener { animation ->

                        header.updateLayoutParams<ViewGroup.LayoutParams> {
                            height = animation.animatedValue as Int
                        }
                        scrollConteudo.updatePadding(top = header.measuredHeight)

                    }
                    duration = 250

                    start()
                }}

                if (scrollY > oldScrollY) {
                    conteudoHeader.animate()
                        .alpha(0f)
                        .setDuration(250)
                        .withEndAction {
                            conteudoHeader.visibility = View.GONE
                            conteudoHeader.alpha = 0f
                            header.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        }
                        .start()
                    }
                }
            else if(scrollY == 0) {

                if (conteudoHeader.visibility == GONE){
                    ValueAnimator.ofInt(alturaFinal, alturaInicial).apply {
                        addUpdateListener { animation ->

                            header.updateLayoutParams<ViewGroup.LayoutParams> {
                                height = animation.animatedValue as Int
                            }
                            scrollConteudo.updatePadding(top = header.measuredHeight)

                        }
                        duration = 250
                        start()
                    }
            }

                conteudoHeader.visibility = View.VISIBLE
                conteudoHeader.animate()
                    .alpha(1f)
                    .setDuration(250)
                    .start()


            }
            scrollConteudo.updatePadding(top = header.measuredHeight)

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets

        }

    }
}
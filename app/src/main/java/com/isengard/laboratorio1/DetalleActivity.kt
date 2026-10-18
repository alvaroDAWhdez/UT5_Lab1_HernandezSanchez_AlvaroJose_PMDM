package com.isengard.laboratorio1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recogo la informacion de la etapa
        val nombreEtapa = intent.getStringExtra("EXTRA_NOMBRE")
        val peligroEtapa = intent.getStringExtra("EXTRA_PELIGRO")

        //Recogo los componnetes e inyecto la informacion
        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvPeligrosidad = findViewById<TextView>(R.id.tvPeligrosidad)

        tvNombre.text = nombreEtapa
        tvPeligrosidad.text = peligroEtapa


    }
}
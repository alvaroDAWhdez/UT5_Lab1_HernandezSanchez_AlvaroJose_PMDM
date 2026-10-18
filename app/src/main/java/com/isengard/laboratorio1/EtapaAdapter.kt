package com.isengard.laboratorio1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class EtapaAdapter (
    private val context: Context,
    private val listaEtapas: List<Etapa>
) : BaseAdapter() {

    // Devuelve el número total de elementos en la lista
    override fun getCount(): Int = listaEtapas.size

    // Devuelve el objeto de una posición concreta
    override fun getItem(position: Int): Any = listaEtapas[position]


    // Devuelve el ID de la fila (normalmente usamos la posición)
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //Reutilizamos la vista si ya existe, si no, la inflamos desde el XML
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_etapa, parent, false)

        //Extraemos el personaje correspondiente a la fila actual
        val etapa = listaEtapas[position]

        //Vinculamos los componenentes del XML de la fila
        val ivEtapa = view.findViewById<ImageView>(R.id.ivEtapa)
        val tvNombre = view.findViewById< TextView>(R.id.tvNombre)
        val tvPeligrosidad = view.findViewById<TextView>(R.id.tvPeligrosidad)

        //Inyectamos los datos del objeto en la vista
        tvNombre.text = etapa.nombre
        tvPeligrosidad.text = etapa.peligrosidad
        ivEtapa.setImageResource(etapa.imagenResId)

        return view
    }
}
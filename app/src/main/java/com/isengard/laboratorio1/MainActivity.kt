package com.isengard.laboratorio1

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Lista global para que la reconozcan los menús
    private lateinit var etapas: List<Etapa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del ListView y la lista de personajes
        val lvEtapas = findViewById<ListView>(R.id.lvEtapas)

        etapas = listOf(
            Etapa("La comarca","Baja",android.R.drawable.ic_menu_compass),
            Etapa("Rivendell","Segura",android.R.drawable.ic_menu_myplaces),
            Etapa("Cima de los Vientos","Alta",android.R.drawable.ic_menu_info_details),
            Etapa("Lothlórien (El Bosque Dorado","Media",android.R.drawable.ic_menu_view)

        )
        val adapter = EtapaAdapter(this, etapas)
        lvEtapas.adapter = adapter

        // Registramos el ListView para el menú contextual (pulsación larga)
        registerForContextMenu(lvEtapas)
    }

    // --- MENÚ CONTEXTUAL (Pulsación larga en la lista) ---
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_inspeccionar, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val seleccionado = etapas[info.position]

        return when (item.itemId) {
            R.id.it_inspeccionar -> {
                // Creamos el Intent Explícito hacia DetalleActivity
                val intent = android.content.Intent(this, DetalleActivity::class.java).apply {
                    // 2. Empaquetamos los datos en el saco de extras
                    putExtra("EXTRA_NOMBRE", seleccionado.nombre)
                    putExtra("EXTRA_PELIGRO", seleccionado.peligrosidad)
                }

                // 3. Lanzamos la actividad de detalle
                startActivity(intent)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    //Dibuja y muestra el menú en la barra superior
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_abandonar, menu)
        return true
    }

    //Captura el clic de Abandonar Misión y cierra la app de forma controlada
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opc_abandonar -> {
                // Cierra esta actividad y todas las de la pila de forma limpia y segura
                finishAffinity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
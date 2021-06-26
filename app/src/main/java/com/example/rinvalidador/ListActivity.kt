package com.example.rinvalidador

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import kotlinx.android.synthetic.main.item_multas.view.*
import okhttp3.OkHttpClient

class ListActivity : AppCompatActivity() {
//    var listCodigo = mutableListOf<String>()
//    var listCodigo: MutableList<String> = mutableListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listLLamado()
//        llenadolistview()
    }


//    fun llenadolistview(){
//
//        val lvdatos = findViewById<ListView>(R.id.lvDatos)
//        val arrayAdapter = ListAdapter(this,listCodigo)
//        lvdatos.adapter = arrayAdapter
//
//    }





    fun listLLamado(){
        var listCodigo = mutableListOf<String>()
//        val listanueva = mutableListOf("Multa${listCodigo}")

        val url1 = "https://apir.apiupbateneo.xyz/Multas/ListMul"

        val queue = Volley.newRequestQueue(this)
        var arrayAdapter:ArrayAdapter<*>
        val lvdatos = findViewById<ListView>(R.id.lvDatos)


        val stringRequest1 = StringRequest(Request.Method.GET,url1, {
                response ->  val klaxonC = Klaxon().parseArray<codigoMulta>(response)

            if (klaxonC != null) {
                for (element in klaxonC){
                    listCodigo.add(element.id.toString())
//                    listCodigo.add(element.codigo)
                }
                arrayAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_expandable_list_item_1,listCodigo)
//                val arrayAdapter = ListAdapter(this,listanueva)

                lvdatos.adapter = arrayAdapter

            }

        }, { Toast.makeText(applicationContext, "algo salio mal", Toast.LENGTH_SHORT).show() })

        queue.add(stringRequest1)

        lvdatos.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ConsultaActivity::class.java)
            intent.putExtra("id", listCodigo[position])
            startActivity(intent)
        }

    }


}




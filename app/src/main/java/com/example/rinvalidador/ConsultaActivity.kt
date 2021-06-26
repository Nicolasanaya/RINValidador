package com.example.rinvalidador

import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_consulta.*
import kotlinx.android.synthetic.main.activity_list.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ConsultaActivity : AppCompatActivity() {
    val modeloEstado:ModeloPatch = ModeloPatch("estado","replace","")
    val modeloObservacion:ModeloPatch = ModeloPatch("observacionEstado","replace","")
    var bundle:Bundle? = null
    var dato:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)
        idtraer()
        consultaagente()
    }

    fun aceptarRegistro(view: View){
        modeloEstado.value = "aceptado"
        enviarPeticionPath()


    }

    fun rechazarRegistro(view: View){
        modeloEstado.value = "rechazado"
        enviarPeticionPath()

    }

    fun enviarPeticionPath(){

        val url1 = "https://apir.apiupbateneo.xyz/Multas/ActMulta/${dato}"
        val queue = Volley.newRequestQueue(this)
        modeloObservacion.value = editTextObservacionesAgente.text.toString()

        val lista= JSONArray()
        var modeloEstadojson = JSONObject()
        modeloEstadojson.put("path",modeloEstado.path)
        modeloEstadojson.put("op",modeloEstado.op)
        modeloEstadojson.put("value",modeloEstado.value)
        var modeloObservacionjson = JSONObject()
        modeloObservacionjson.put("path",modeloObservacion.path)
        modeloObservacionjson.put("op",modeloObservacion.op)
        modeloObservacionjson.put("value",modeloObservacion.value)
        lista.put(modeloEstadojson)
        lista.put(modeloObservacionjson)
        Log.d("RIN-lista",lista.toString())


        val peticionPatch = JsonArrayRequest(
            Request.Method.PATCH,
            url1,
            lista,
            { response ->
                Toast.makeText(applicationContext, "exitoso", Toast.LENGTH_LONG).show()
            },
            {  error ->
                Log.d("RIN-error",error.toString())
            }
        )
        queue.add(peticionPatch)
    }

    fun idtraer(){
        bundle = intent.extras
        dato = bundle?.getString("id")
    }


    fun consultaagente() {
        var datosMulta = mutableListOf<String>()
        val Oplaca = findViewById<TextView>(R.id.textObtencionPlaca)
        val Ocodigo = findViewById<TextView>(R.id.textObtencionMulta)
        val odescripcion = findViewById<TextView>(R.id.textDescripcioncodigo)
        val oobservacion = findViewById<TextView>(R.id.textObtencionObservacion)


        val url1 = "https://apir.apiupbateneo.xyz/Multas/IdMul/${dato}"
        val queue = Volley.newRequestQueue(this)


        val stringRequest = StringRequest(Request.Method.GET, url1, { response ->
            val klaxon = Klaxon().parse<datos>(response)

            if (klaxon != null) {
                Oplaca.text = klaxon.placa
                Ocodigo.text = klaxon.codigo
                odescripcion.text = klaxon.descripcion
                oobservacion.text = klaxon.observacion
                Picasso.get()
                    .load(klaxon.foto)
                    .into(imgFoto)
            }

        }, { Toast.makeText(applicationContext, "algo salio mal", Toast.LENGTH_SHORT).show() })

        queue.add(stringRequest)
    }


}
package com.example.rinvalidador

import android.content.Context
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

class ListAdapter(private val mContext: Context, private val listaCodigo: MutableList<codigoMulta>) : ArrayAdapter<codigoMulta>(mContext, 0, listaCodigo) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_multas, parent,false)

        val codigoMulta = listaCodigo[position]

        layout.textIDCodigo.text = "ID MULTA = ${codigoMulta.id}"
        layout.textCodMulta.text = "ID MULTA = ${codigoMulta.codigo}"

        return layout
    }
}

//class ListAdapter(private val mContext: Context, private val listaCodigo: MutableList<String>) : ArrayAdapter<String>(mContext, 0, listaCodigo)  {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_multas, parent,false)
//
//        layout.textIDCodigo.text = "ID MULTA = ${codigoMulta.id}"
//        layout.textCodMulta.text = "ID MULTA = ${codigoMulta.codigo}"
//
//        return layout
//    }
//}


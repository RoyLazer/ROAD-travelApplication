package com.rone.road.fragmentExplore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.rone.road.MainActivity
import com.rone.road.R
import com.rone.road.R.layout.fragment_explore
import com.rone.road.explorerAdapter
import com.rone.road.explorerTodo
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws

class exploreFragment : Fragment(){
    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(fragment_explore, container, false)
        val queue = Volley.newRequestQueue(view.context)
        var lista = mutableListOf<explorerTodo>()
        val URL_LIST = "http://192.168.0.6/webservice/webservice_travel.php"
        val stringRequest = object : StringRequest( // Creamos la peticion
            Request.Method.POST, URL_LIST, // Decimos que es metodo POST, y mandamos el URL
            Response.Listener<String> { response -> //Listener para obtener las respuestas
                try {
                    val obj = JSONArray(response) //Obtenemos el String del JSON
                    //Obtenemos el mensaje del JSON
                    Toast.makeText(view.context, "Estos son los viajes encontrados" , Toast.LENGTH_LONG).show()
                    Log.i("RESULT", obj.toString())
                    var listTodo = mutableListOf<explorerTodo>()
                    for(i in 0 until obj.length()){
                        val jsonobj = obj.getJSONObject(i)
                        Log.i("QUERYES", "${jsonobj.getString("id").toInt()} ${jsonobj.getString("destino")} ${jsonobj.getString("personas")} ${jsonobj.getString("dias")} ${jsonobj.getString("precio")} ${jsonobj.getString("partida")}")
                        val id: Int = jsonobj.getString("id").toInt()
                        val destino:String = jsonobj.getString("destino")
                        val personas:String = jsonobj.getString("personas")
                        val dias:String = jsonobj.getString("dias")
                        val precio:String = jsonobj.getString("precio")
                        val partida:String = jsonobj.getString("partida")
                        val explorer = explorerTodo(id,"Viaje a ${destino}",personas," ${dias} Días","${precio}$ MXN",partida)
                        lista.add(explorer)
                    }
                    // Si existe un error en la autenticacion
                } catch (e: JSONException) {
                    //Imprime el Error
                        Toast.makeText(view.context, "ERROR: ${e.printStackTrace()}", Toast.LENGTH_LONG).show()
                }
                val adapter = explorerAdapter(lista)
                view.findViewById<RecyclerView>(R.id.rvExplorer).layoutManager = LinearLayoutManager(activity)
                view.findViewById<RecyclerView>(R.id.rvExplorer).adapter = adapter
            },
            // Si hay un error con la conexion mandamos un mensaje
            Response.ErrorListener { Toast.makeText(view.context, "Parece que hubo un error con la conexion!", Toast.LENGTH_LONG).show() }) {
            //Metodo que lanza un error si falla
        }
        //Lanzamos nuestro Objetos de la conexion.
        queue.add(stringRequest)



    return view
    }







}




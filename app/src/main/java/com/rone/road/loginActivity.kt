package com.rone.road

import android.content.Intent
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.jvm.Throws


class loginActivity : AppCompatActivity() {

    val URL_LOGIN = "http://192.168.0.6/webservice/webservice_login.php"
    val AUTH_CODE = "#10a897f62d37"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        startBackgroundVideo()
    }


    fun newLogin(view: View){
        // Creamos el objeto de Volley, que es la libreria que nos poermite crear la conexion
        val queue = Volley.newRequestQueue(this)

        val emailb: EditText = findViewById(R.id.loginEmail) // Inicializamos las variables email
        val passb: EditText = findViewById(R.id.loginPassword) // Inicializamos el campo pass
        val email = emailb.text.toString() //Extraemos los valores de email
        val pass = passb.text.toString() //Extraemos los valores de pass


        val stringRequest = object : StringRequest( // Creamos la peticion
            Request.Method.POST, URL_LOGIN, // Decimos que es metodo POST, y mandamos el URL
            Response.Listener<String> { response -> //Listener para obtener las respuestas
                try {
                    val obj = JSONObject(response) //Obtenemos el String del JSON
                    //Obtenemos el mensaje del JSON
                    Toast.makeText(this, obj.getString("message"), LENGTH_LONG).show()
                    //Si el codigo de autenticacion es igual al nuestro
                    if(obj.getString("auth") == AUTH_CODE){
                        //Creamos el intent
                        val startLogin = Intent(this, MainActivity::class.java)
                        // Lanzamos el Intent
                        startActivity(startLogin)
                        // Terminamos la activity
                        finish()
                    }
                // Si existe un error en la autenticacion
                } catch (e: JSONException) {
                    //Imprime el Error
                    e.printStackTrace()
                }
            },
            // Si hay un error con la conexion mandamos un mensaje
            Response.ErrorListener { Toast.makeText(applicationContext, "Parece que hubo un error con la conexion!", Toast.LENGTH_LONG).show() }) {
            //Metodo que lanza un error si falla
            @Throws(AuthFailureError::class)
            //Sobreescribimos la funcion getParams() y le pedimos que retorn un Map
            override fun getParams(): Map<String,String>{
                //declaramos el Map
                val params = HashMap<String,String>()
                // Agregamos el Key y le damos el valor de nuestro campo
                params.put("email",email)
                params.put("contrasena",pass)
                // Retornamos los parametros
                return params
            }
        }
        //Lanzamos nuestro Objetos de la conexion.
        queue.add(stringRequest)


    }
    fun register(view: View){
        val startRegister = Intent(this, registerActivity::class.java)
        startActivity(startRegister)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        startBackgroundVideo()
    }

    private fun startBackgroundVideo(){
        val videoview = findViewById<View>(R.id.videoView) as VideoView
        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.videobackground)
        videoview.setVideoURI(uri)
        videoview.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })
        videoview.start()
    }





}
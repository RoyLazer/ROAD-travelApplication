package com.rone.road

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws

class registerActivity : AppCompatActivity() {

    val URL_REGISTER = "http://192.168.0.6/webservice/webservice_register.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }


    fun newRegister(view: View){
        // Inicializamos el Objeto Volley de la conexion
        val queue = Volley.newRequestQueue(this)

        val nameb: EditText = findViewById(R.id.registerName)// Inicializamos el campo Name
        val emailb: EditText = findViewById(R.id.registerEmail)// Inicializamos el campo Email
        val passwordb: EditText = findViewById(R.id.registerPassword)// Inicializamos el campo Password
        val confirmPasswordb: EditText = findViewById(R.id.registerConfirmPassword)// Inicializamos el campo confirmPass
        val name = nameb.text.toString() // Extraemos el valor de Name
        val email = emailb.text.toString()// Extraemos el valor de Email
        val password = passwordb.text.toString()// Extraemos el valor de password
        val confirmPassword = confirmPasswordb.text.toString()// Extraemos el valor de confirmpassword

        if(password == confirmPassword){ // Si ambos passwords coinciden entonces
            val stringRequest = object : StringRequest( // Lanzamos el request
                Request.Method.POST, URL_REGISTER, // Pasamos el metodo POST y la URL
                Response.Listener<String> { response -> // Agregamos el Listener de la URL
                    try {
                        val obj = JSONObject(response)// Obtenemos los valores que nos devuelve
                        //Lanzamos una confirmacion
                        Toast.makeText(this, obj.getString("message"), Toast.LENGTH_LONG).show()
                    //Si hay un error leyendo los elementos
                    } catch (e: JSONException) {
                        //Imprimimos el Error
                        e.printStackTrace()
                    }
                },
                //colocamos un listener por si la conexion falla
                Response.ErrorListener { Toast.makeText(applicationContext, "Parece que hubo un error con la conexion!", Toast.LENGTH_LONG).show() }) {
                //Metodo que lanza un error si falla
                @Throws(AuthFailureError::class)
                //Sobreescribimos la funcion getParams() y le pedimos que retorn un Map
                override fun getParams(): Map<String,String>{
                    //declaramos el Map
                    val params = HashMap<String,String>()
                    // Agregamos el Key y le damos el valor de nuestro campo
                    params.put("name",name)
                    params.put("email",email)
                    params.put("contrasena", password)
                    // Retornamos los parametros
                    return params
                }
            }
            //Lanzamos nuestro Objetos de la conexion.
            queue.add(stringRequest)

        }else{ // Si no coinciden los passwords entonces le notificamos al Usuario
            Toast.makeText(this, "Las contraseñas no coinciden, porfavor, vuelve a escribir este campo",Toast.LENGTH_LONG).show()
        }




    }
}
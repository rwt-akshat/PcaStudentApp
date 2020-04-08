package com.akrwt.pcastudentapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private var loginBtn: Button? = null
    private var progressBar: ProgressBar? = null
    private var etUID:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.loginBtn)
        progressBar = findViewById(R.id.progressBar)
        etUID = findViewById(R.id.etUID)

        loginBtn!!.setOnClickListener {
            progressBar!!.visibility = View.VISIBLE
            login()
        }
    }

    private fun login() {
        val uid = etUID!!.text.toString().trim()

        when {
            uid.isEmpty() -> {
                progressBar!!.visibility = View.INVISIBLE
                etUID!!.error = "Unique ID cannot be empty"
                etUID!!.requestFocus()
            }
            uid.length < 8 -> {
                progressBar!!.visibility = View.INVISIBLE
                Toast.makeText(
                    applicationContext,
                    "Unique ID should be of length 8",
                    Toast.LENGTH_SHORT
                ).show()
            }
            CheckConnection().checkConnection(this) == 0 -> {
                progressBar!!.visibility = View.INVISIBLE
                Toast.makeText(
                    applicationContext,
                    "Check your internet connection..",
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                getStudentData(uid)
            }
        }
    }

    private fun getStudentData(uID: String) {

        val url =
            "https://script.google.com/macros/s/AKfycbwvAfJUnGxT8zr_cXqvM47mysGSRMnfgghXTlJFB8-lp191pIcN/exec?action=searchStudent&id=$uID"

        val request = StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                parseJsonResponse(it)

            },
            Response.ErrorListener {
                progressBar!!.visibility = View.INVISIBLE
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()

            })
        Volley.newRequestQueue(this).add(request)
    }

    private fun parseJsonResponse(jsonResponse: String) {

        val sharedPref = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref!!.edit()

        try {
            val jsonObj = JSONObject(jsonResponse)
            val jsonArray = jsonObj.getJSONArray("sDetail")
            val jO = jsonArray.getJSONObject(0)

            val id = jO.getString("id")
            val name = jO.getString("name")
            val doB = jO.getString("dob")
            val mClass = jO.getString("class")
            val contact = jO.getString("contact")
            val fName = jO.getString("fName")
            val mName = jO.getString("mName")

            editor.putString("id", id)
            editor.putString("name", name)
            editor.putString("doB", doB)
            editor.putString("mClass", mClass)
            editor.putString("contact", contact)
            editor.putString("fName", fName)
            editor.putString("mName", mName)

            editor.apply()
            progressBar!!.visibility = View.INVISIBLE
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        } catch (ex: Exception) {
            progressBar!!.visibility = View.INVISIBLE
            Toast.makeText(
                applicationContext,
                "Student ID not found...try again...",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val preferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val pref = preferences.getString("id", "default")
        if (pref != "default") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}

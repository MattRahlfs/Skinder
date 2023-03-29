package com.example.skinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            fun getLoginCredentials(): Pair<String,String>{
                val loginName = findViewById<EditText>(R.id.etName).text.toString()
                val loginPassword = findViewById<EditText>(R.id.etPassword).text.toString()

                return Pair(loginName, loginPassword)
            }
            fun verifyLoginCredentials(loginName: String, loginPassword: String):Boolean{

                return (loginName == "1") && (loginPassword == "1")
            }

            val loginButton: Button = findViewById(R.id.btnLogin)
                loginButton.setOnClickListener{

                    val credentials = getLoginCredentials().toList()

                    if (verifyLoginCredentials(credentials[0],credentials[1]))
                        println("verified")
                    else
                        println("failed")

                }


    }
}
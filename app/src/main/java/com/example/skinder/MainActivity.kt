package com.example.skinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun getLoginCredentials(): Pair<String,String>{
                val loginName = findViewById<EditText>(R.id.etEmailAddress).text.toString()
                val loginPassword = findViewById<EditText>(R.id.etPassword).text.toString()

                return Pair(loginName, loginPassword)
            }
//        fun verifyLoginCredentials(loginName: String, loginPassword: String):Boolean{
//                return (loginName == "1") && (loginPassword == "1")
//            }

        val loginButton: Button = findViewById(R.id.btnLogin)
        loginButton.setOnClickListener{
                val db = DatabaseHelper(this, null)
                val credentials = getLoginCredentials().toList()
                val accountQuery = db.verifyAccountExists(credentials[0])
                accountQuery!!.moveToFirst()
                val accountExists = accountQuery.getString(0).toInt()
                val accountPassword = db.getPasswordFromAccount(credentials[0])
                accountPassword!!.moveToFirst()


                if (accountExists == 1 && accountPassword.getString(0) == credentials[1]) {
                    val accountPassword = db.getPasswordFromAccount(credentials[0])
                    val loginActivityIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                    startActivity(loginActivityIntent)


                }
                else{Toast.makeText(this, "Those credentials were invalid", Toast.LENGTH_LONG).show()}
            }

        val registerButton: Button = findViewById(R.id.btnRegister)
        registerButton.setOnClickListener{
            val registerActivityIntent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(registerActivityIntent)
        }



    }
}
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

        //get login creds just pulls the info from the text and password fields and returns them
        fun getLoginCredentials(): Pair<String,String>{
                val loginName = findViewById<EditText>(R.id.etEmailAddress).text.toString()
                val loginPassword = findViewById<EditText>(R.id.etPassword).text.toString()

                return Pair(loginName, loginPassword)
            }
//        fun verifyLoginCredentials(loginName: String, loginPassword: String):Boolean{
//                return (loginName == "1") && (loginPassword == "1")
//            }

        //when the loginb button is clicked the get login creds is callled and then passes the information to the database helper to verifiy the account exists
        // it also receives the password from the database and compares against what is input, if it matches there is a valid login
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

        //registration button to take you to the registration activity 
        val registerButton: Button = findViewById(R.id.btnRegister)
        registerButton.setOnClickListener{
            val registerActivityIntent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(registerActivityIntent)
        }



    }
}
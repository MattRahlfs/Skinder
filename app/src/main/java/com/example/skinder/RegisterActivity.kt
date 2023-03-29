package com.example.skinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.core.view.get

private class UserDetailReturn<fn, ln, ea, DOB>(
    val firstName:fn,
    val lastName:ln,
    val emailAddress:ea,
    val birthDate:DOB

)
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fun getUserDetails():UserDetailReturn<String, String, String, String>{

            val firstName = findViewById<EditText>(R.id.etFirstName).text.toString()
            val lastName = findViewById<EditText>(R.id.etLastName).text.toString()
            val emailAddress = findViewById<EditText>(R.id.eteEmailAddress).text.toString()
            val datePickerUnit = findViewById<DatePicker>(R.id.dpDOBPicker)
            val day = datePickerUnit.dayOfMonth
            val month = (datePickerUnit.month + 1)
            var year = datePickerUnit.year.toString()
            val birthDate = day.toString()+month.toString()+year.toString()

            if (day < 10){
                var dayReal = day.toString()
                dayReal = "0" + dayReal
                val monthReal = day.toString()

                val birthDate = dayReal+monthReal+year
                return UserDetailReturn(firstName, lastName, emailAddress, birthDate)

            }else if(month < 10){
                var monthReal = day.toString()
                monthReal = "0" + monthReal
                val dayReal = day.toString()

                val birthDate = dayReal+monthReal+year
                return UserDetailReturn(firstName, lastName, emailAddress, birthDate)

            }else if ((day < 10) && (month < 10)){
                var dayReal = day.toString()
                dayReal = "0" + dayReal

                var monthReal = day.toString()
                monthReal = "0" + monthReal


                val birthDate = dayReal+monthReal+year

                return UserDetailReturn(firstName, lastName, emailAddress, birthDate)
            }else{
                return UserDetailReturn(firstName, lastName, emailAddress, birthDate)
            }

        }

        val createAccountButton: Button = findViewById(R.id.btnCreateAccount)
        createAccountButton.setOnClickListener{
            println(getUserDetails().firstName)
        }



    }
}
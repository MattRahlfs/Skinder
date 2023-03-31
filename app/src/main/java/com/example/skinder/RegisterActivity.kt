package com.example.skinder


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import android.database.sqlite.SQLiteDatabase

private class UserDetailReturn<fn, ln, ea, DOB, pw>(
    val firstName:fn,
    val lastName:ln,
    val emailAddress:ea,
    val birthDate:DOB,
    val accountPassword:pw

)
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        fun getUserDetails(): UserDetailReturn<String, String, String, String, String> {

            val firstName = findViewById<EditText>(R.id.etFirstName).text.toString()
            val lastName = findViewById<EditText>(R.id.etLastName).text.toString()
            val emailAddress = findViewById<EditText>(R.id.eteEmailAddress).text.toString()
            val datePickerUnit = findViewById<DatePicker>(R.id.dpDOBPicker)
            val accountPassword = findViewById<EditText>(R.id.etaccountPassword).text.toString()
            val day = datePickerUnit.dayOfMonth
            val month = (datePickerUnit.month + 1)
            val year = datePickerUnit.year.toString()
            var birthDate = day.toString() + month.toString() + year


            if ((day < 10) && (month < 10)) {
                var dayReal = day.toString()
                dayReal = "0" + dayReal
                var monthReal = month.toString()
                monthReal = "0" + monthReal

                var birthDate = dayReal + monthReal + year
                return UserDetailReturn(firstName, lastName, emailAddress, birthDate, accountPassword)

            } else if (day < 10) {
                var dayReal = day.toString()
                dayReal = "0" + dayReal
                var monthReal = month.toString()

                var birthDate = dayReal + monthReal + year
                return UserDetailReturn(firstName, lastName, emailAddress, birthDate, accountPassword)

            } else if (month < 10) {
                var monthReal = month.toString()
                monthReal = "0" + monthReal

                var dayReal = day.toString()


                var birthDate = dayReal + monthReal + year

                return UserDetailReturn(firstName, lastName, emailAddress, birthDate, accountPassword)
            } else {
                return UserDetailReturn(firstName, lastName, emailAddress, birthDate, accountPassword)
            }


        }

        val createAccountButton: Button = findViewById(R.id.btnCreateAccount)
        createAccountButton.setOnClickListener {
            var userDetails = getUserDetails()
            val db = DatabaseHelper(this, null)
            println(userDetails.firstName)
            println(userDetails.lastName)
            println(userDetails.emailAddress)
            println(userDetails.birthDate)
            println(userDetails.accountPassword)


            db.createNewAccount(userDetails.firstName, userDetails.lastName, userDetails.emailAddress, userDetails.birthDate, userDetails.accountPassword)
            Toast.makeText(this, "${userDetails.emailAddress}  was added to the database", Toast.LENGTH_LONG).show()




        }

        val viewDatabaseButton: Button = findViewById(R.id.btnviewDatabase)
        viewDatabaseButton.setOnClickListener{
            val db = DatabaseHelper(this, null)
            val cursor = db.getName()
            cursor!!.moveToFirst()
            while(cursor.moveToNext()) {
                println(cursor.getString(0))


            }

            cursor.close()

        }



    }
}
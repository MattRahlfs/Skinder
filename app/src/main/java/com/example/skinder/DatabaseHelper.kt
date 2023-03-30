package com.example.skinder

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "skinder"
        private val DATABASE_VERSION = 1
        private val TABLE_NAME = "tbl_skinder_accounts"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val databaseInitializerQuery =
            ("CREATE TABLE $TABLE_NAME (emailAddress STRING PRIMARY KEY, firstName STRING, lastName STRING, birthDate STRING)")

        db.execSQL(databaseInitializerQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun createNewAccount(
        firstName: String,
        lastName: String,
        emailAddress: String,
        birthDate: String
    ) {

        val values = ContentValues()

        values.put("firstName", firstName)
        values.put("lastName", lastName)
        values.put("emailAddress", emailAddress)
        values.put("birthDate", birthDate)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()

    }

    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)


    }

}
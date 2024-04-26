package com.example.kotlin.datastore
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class SQLiteHelperFriends(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object{
        private const val DATABASE_NAME ="Database.db"
        private const val TABLE_NAME  = "Friends"
        private const val ID ="ID"
        private const val NAME = "Name"
        private const val NUMBER  ="Number"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT, $NUMBER TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertFriend(name: String, number: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(NAME, name)
            put(NUMBER, number)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}
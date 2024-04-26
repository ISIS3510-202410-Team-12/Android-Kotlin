package com.example.kotlin.datastore

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperEvents(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Database.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Events"
        private const val COLUMN_ID = "ID"
        private const val COLUMN_CLASS_NAME = "ClassName"
        private const val COLUMN_TEACHER = "Teacher"
        private const val COLUMN_REMINDER = "Reminder"
        private const val COLUMN_LABEL = "Label"
        private const val COLUMN_DAY = "Day" // Separate column for Day
        private const val COLUMN_HOUR = "Hour" // Separate column for Hour
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_CLASS_NAME TEXT, $COLUMN_TEACHER TEXT, $COLUMN_REMINDER TEXT, $COLUMN_LABEL TEXT, $COLUMN_DAY TEXT, $COLUMN_HOUR TEXT)" // Added Day and Hour columns
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertEvent(className: String, teacher: String, reminder: String, label: String, day: String, hour: String) { // Added Day and Hour parameters
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CLASS_NAME, className)
            put(COLUMN_TEACHER, teacher)
            put(COLUMN_REMINDER, reminder)
            put(COLUMN_LABEL, label)
            put(COLUMN_DAY, day) // Insert Day
            put(COLUMN_HOUR, hour) // Insert Hour
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}
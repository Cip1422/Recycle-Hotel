package com.example.notes_application.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.notes_application.model.Notes
import com.example.notes_application.view.MainActivity
import java.text.FieldPosition

class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context,
        DATABASE_NAME, null,
        DATABASE_VERSION, null) {


    companion object {
        const val DATABASE_NAME = "notes.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "note_table"
        const val NOTE_ID = "note_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_NOTE = "note"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createStatement =
            "CREATE TABLE $TABLE_NAME ($NOTE_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_NOTE TEXT )"
        db.execSQL(createStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertNote(newNote: Notes) {
        val noteValues = ContentValues()
        noteValues.put(COLUMN_TITLE, newNote.note_to_title())
        noteValues.put(COLUMN_NOTE, newNote.notes)
        val db = writableDatabase
        db.insert(TABLE_NAME, null, noteValues)
        db.close()
    }

    fun readAllNotes(): Cursor {
        val query = "SELECT * FROM $TABLE_NAME"
        val db = readableDatabase
        return db.rawQuery(query, null)
    }


    fun deleteNotes(position: Int){
        val db = writableDatabase
        db.delete(TABLE_NAME, NOTE_ID+"="+position, null)
    }

}
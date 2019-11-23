package com.example.notes_application.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_application.Adaptor.Note_Adaptor
import com.example.notes_application.Database.MyDatabaseHelper
import com.example.notes_application.R
import com.example.notes_application.model.Notes
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {

    lateinit var sharedPrefs: SharedPreferences
    private lateinit var myDatabaseHelper: MyDatabaseHelper
    private var noteList = mutableListOf<Notes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        myDatabaseHelper = MyDatabaseHelper(this)

        sharedPrefs = this.getSharedPreferences("Notes_File", 0)


        val color = sharedPrefs.getInt("value", 0)



        note_text.setTextColor(color)
        note_text.setHintTextColor(color)



        save.setOnClickListener {
            saveToDatabase()

            //intent = Intent(this, Display_note::class.java)
           // startActivity(intent)
        }




    }

    override fun onResume() {
        super.onResume()
        readFromDatabase()
    }



    private fun readFromDatabase() {
        noteList = mutableListOf()

        val cursor = myDatabaseHelper.readAllNotes()
        cursor.moveToFirst()

        if (cursor.count > 0) {
            val note = Notes(
                cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NOTE))
            )
            noteList.add(note)
        }
        while (cursor.moveToNext()) {
            val noteTitle = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_TITLE))
            val noteText =
                cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NOTE))
            val readNote = Notes(noteTitle, noteText)
            noteList.add(readNote)
        }
        displayUsers()

    }


    private fun saveToDatabase() {
        val noteText = note_text.text.toString()
        val noteTitle = noteText.substringBefore( " ")
        val newNote = Notes(noteTitle, noteText)
        myDatabaseHelper.insertNote(newNote)
        Toast.makeText(this, "Note added to database.", Toast.LENGTH_SHORT).show()
        note_text.text.clear()
        readFromDatabase()
    }


    private fun displayUsers() {

        note_items.adapter = Note_Adaptor(noteList)
        note_items.layoutManager = LinearLayoutManager(this)
        (note_items.adapter as Note_Adaptor).notifyDataSetChanged()

    }


}

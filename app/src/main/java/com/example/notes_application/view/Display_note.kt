package com.example.notes_application.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes_application.Database.MyDatabaseHelper
import com.example.notes_application.R
import com.example.notes_application.model.Notes
import kotlinx.android.synthetic.main.activity_display_note.*

class Display_note : AppCompatActivity() {

    private lateinit var myDatabaseHelper: MyDatabaseHelper
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_note)
        sharedPrefs = this.getSharedPreferences("Notes_File", 0)


        val color = sharedPrefs.getInt("value", 0)
        edit_notes.setTextColor(color)

        myDatabaseHelper = MyDatabaseHelper(this)


        var position = intent.getIntExtra("Position", 0)
        position += 1

        getTheNote(position)

        return_button.setOnClickListener {
            intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        save_the_note.setOnClickListener {


            myDatabaseHelper.deleteNotes(position)
            saveToDatabase()


        }



    }


    fun getTheNote(position: Int){

        val cursor = myDatabaseHelper.readAllNotes()

        cursor.move(position)

        val noteText =
            cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NOTE))

        edit_notes.setText(noteText)

    }

    private fun saveToDatabase() {
        val noteText = edit_notes.text.toString()
        val noteTitle = noteText.substringBefore( " ")
        val newNote = Notes(noteTitle, noteText)
        myDatabaseHelper.insertNote(newNote)
        Toast.makeText(this, "Note added to changed.", Toast.LENGTH_SHORT).show()

    }

}

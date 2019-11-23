package com.example.notes_application.Adaptor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_application.R
import com.example.notes_application.model.Notes
import com.example.notes_application.view.Display_note

class Note_Adaptor(val noteList: List<Notes>) :
    RecyclerView.Adapter<Note_Adaptor.MyCustomViewHolder>() {


    lateinit var sharedPrefs: SharedPreferences
    lateinit var context: Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_item_layout, parent, false)

        sharedPrefs = parent.context.getSharedPreferences("Notes_File", 0)
        context = parent.context






        return MyCustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(viewHolder: MyCustomViewHolder, position: Int) {
        viewHolder.apply {



            val color = sharedPrefs.getInt("value", 0)
            titleText.setTextColor(color)
            titleText.text = noteList[position].title

            titleText.setOnClickListener{

                val intent = Intent(context, Display_note::class.java)

                intent.putExtra("Position", position)
                context.startActivity(intent)
            }




        }
    }




    inner class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.title_text)

    }
}
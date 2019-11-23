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
import com.example.notes_application.model.Color
import com.example.notes_application.view.NotesActivity


class Color_Adaptor(val colorList: List<Color>) :
    RecyclerView.Adapter<Color_Adaptor.MyCustomViewHolder>() {


    lateinit var sharedPrefs: SharedPreferences
    lateinit var spEditor: SharedPreferences.Editor
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.color_item_layout, parent, false)

         context = parent.context
        sharedPrefs = context.getSharedPreferences("Notes_File", 0)
        spEditor = sharedPrefs.edit()

        return MyCustomViewHolder(view)

    }





    override fun getItemCount(): Int {
       return colorList.size
    }

    override fun onBindViewHolder(viewHolder: MyCustomViewHolder, position: Int) {



        viewHolder.apply {
            color_to_show.text = colorList[position].name
            color_to_show.setTextColor(colorList[position].code)

            color_to_show.setOnClickListener{


                spEditor.putInt("value", colorList[position].code)
                spEditor.commit()



                context.startActivity(Intent(context, NotesActivity::class.java))
            }

        }




    }


    class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val color_to_show: TextView = itemView.findViewById(R.id.color_item)




    }

}




package com.example.notes_application.view

import android.content.Intent
import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.notes_application.Adaptor.Color_Adaptor
import com.example.notes_application.R
import com.example.notes_application.model.Color
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var colorList: MutableList<Color>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemDecoration = DividerItemDecoration(this, VERTICAL)
        color_view.addItemDecoration(itemDecoration)


        setList()
        setUpRecyclerView()

        Notes_button.setOnClickListener {
            startNotes()
        }


    }


    fun setList(){

        colorList = mutableListOf()

        var color0 = Color("silver", parseColor("silver"))
        var color1 = Color("red", parseColor("red"))
        var color2 = Color("blue", parseColor("blue"))
        var color3 = Color("green", parseColor("green"))
        var color4 = Color("black", parseColor("black"))
        var color5 = Color("gray", parseColor("gray"))
        var color6 = Color("cyan", parseColor("cyan"))
        var color7 = Color("yellow", parseColor("yellow"))
        var color8 = Color("magenta", parseColor("magenta"))
        var color9 = Color("maroon", parseColor("maroon"))
        var color10 = Color("purple", parseColor("purple"))
        var color11 = Color("teal", parseColor("teal"))


        colorList.add(color0)
        colorList.add(color1)
        colorList.add(color2)
        colorList.add(color3)
        colorList.add(color4)
        colorList.add(color5)
        colorList.add(color6)
        colorList.add(color7)
        colorList.add(color8)
        colorList.add(color9)
        colorList.add(color10)
        colorList.add(color11)

    }


   private  fun setUpRecyclerView(){
        color_view.adapter = Color_Adaptor(colorList)
        color_view.layoutManager = LinearLayoutManager(this)
        (color_view.adapter as Color_Adaptor).notifyDataSetChanged()
    }


    fun startNotes(){
        intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)

    }


}

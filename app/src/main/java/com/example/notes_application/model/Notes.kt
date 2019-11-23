package com.example.notes_application.model

class Notes (var title: String, var notes: String){



     fun note_to_title():String{

        this.title = this.notes.substringBefore( " ")


        return title
    }


}
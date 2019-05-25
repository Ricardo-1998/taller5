package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "author_table")
data class Autor(
    val nombre:String = "N/A"
){
    @PrimaryKey(autoGenerate = true)
    var idAutor:Int = 0
}
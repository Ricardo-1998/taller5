package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "author_table")
data class Autor(
    @PrimaryKey
    var idAutor:Int = 0,
    val nombre:String = "N/A"
)
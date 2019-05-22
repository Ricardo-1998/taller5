package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "author_table")
data class Autor(
    val nombre:String,
    val fechaNacimiento:Date
){
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0

}
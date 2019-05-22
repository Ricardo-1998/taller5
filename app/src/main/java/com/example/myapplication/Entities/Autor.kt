package com.example.myapplication.Entities

import androidx.room.Entity
import java.util.*

@Entity(tableName = "author_table")
data class Autor(
    val nombre:String,
    val fechaNacimiento:Date
)
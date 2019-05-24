package com.example.myapplication.Entities

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_table")
data class Libro(
    @PrimaryKey val isbn: String,
    var autores: String,
    val editorial: String,
    val nombre:String,
    val caratula:String,
    val tags:String
)
package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_table")
data class Libro(
    @PrimaryKey val isbn: String,
    val autores: List<String>,
    val editorial: String,
    val nombre:String,
    val caratula:String,
    val tags:String
)
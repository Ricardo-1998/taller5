package com.example.myapplication.Entities


import androidx.room.*
import com.example.myapplication.typeConvert.ListConverter


@Entity(tableName = "book_table")
data class Libro(
    @PrimaryKey val isbn: String,
    @ColumnInfo(name="c_autores")
    var autores: Int,
    @ColumnInfo(name="c_editorial")
    val editorial: String,

    @ColumnInfo(name="c_nombre")
    val nombre:String,

    @ColumnInfo(name="c_caratula")
    val caratula:String
)
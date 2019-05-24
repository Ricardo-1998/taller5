package com.example.myapplication.Entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_table")
data class Libro(
    @PrimaryKey val isbn: String,
    @ColumnInfo(name="c_autores") val autores: String,
    @ColumnInfo(name="c_editorial")val editorial: String,
    @ColumnInfo(name="c_nombre")val nombre:String,
    @ColumnInfo(name="c_caratula")val caratula:String,
    @ColumnInfo(name="c_tag")val tags:String
)
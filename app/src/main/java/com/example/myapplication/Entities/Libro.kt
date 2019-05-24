package com.example.myapplication.Entities

<<<<<<< HEAD
import androidx.lifecycle.LiveData
=======
import androidx.room.ColumnInfo
>>>>>>> 1da17190ce56c81ed01fd1d8db717c5f8e163739
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
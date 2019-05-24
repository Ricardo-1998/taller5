package com.example.myapplication.Entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "book_table",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Autor::class,
            parentColumns = ["id"],
            childColumns = ["c_autores"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["id"],
            childColumns = ["c_tag"],
            onDelete = ForeignKey.CASCADE)
        )
    )
data class Libro(
    @PrimaryKey val isbn: String,
    @ColumnInfo(name="c_autores")
    var autores: List<Int>,
    @ColumnInfo(name="c_editorial")
    val editorial: String,
    @ColumnInfo(name="c_nombre")
    val nombre:String,
    @ColumnInfo(name="c_caratula")
    val caratula:String,
    @ColumnInfo(name="c_tag")
    var tags: List<Int>
)
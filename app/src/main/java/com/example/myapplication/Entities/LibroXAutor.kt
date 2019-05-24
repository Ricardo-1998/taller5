package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity(tableName = "bookXauthor_table",
        foreignKeys = arrayOf(
            ForeignKey(
                entity = Autor::class,
                parentColumns = ["id"],
                childColumns = ["idAutor"],
                onDelete = CASCADE),
            ForeignKey(
                entity = Libro::class,
                parentColumns = ["isbn"],
                childColumns = ["isbnLibro"],
                onDelete = CASCADE)
        )
)
data class LibroXAutor (
    @PrimaryKey
    val id:Int = 0,
    val idAutor:Int = 0,
    val isbnLibro:String = ""
)
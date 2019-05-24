package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "bookXtag_table",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["id"],
            childColumns = ["idTag"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Libro::class,
            parentColumns = ["isbn"],
            childColumns = ["isbnLibro"],
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class LibroXTag (
    @PrimaryKey
    val id:Int = 0,
    val idTag:Int = 0,
    val isbnLibro:String = ""
)
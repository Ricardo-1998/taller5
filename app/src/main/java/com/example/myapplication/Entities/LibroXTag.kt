package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "bookXtag_table")
data class LibroXTag (
    val idTag:Int = 0,
    val isbnLibro:String = ""
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
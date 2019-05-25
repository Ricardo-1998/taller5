package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_table")
class Tag(
    val type:String = "N/A"
){
    @PrimaryKey(autoGenerate = true)
    var idTag:Int = 0
}
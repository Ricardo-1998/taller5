package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_table")
class Tag(
    @PrimaryKey
    var idTag:Int =0,
    val type:String = "N/A"
)
package com.example.myapplication.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date



@Entity(tableName = "author_table")
data class Autor(
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,

    val nombre:String
)
package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Dao.*
import com.example.myapplication.Entities.*

@Database(entities = [Autor::class,Libro::class,Tag::class,LibroXTag::class]
    ,version = 1,exportSchema = false)
abstract class LibraryDatabase:RoomDatabase() {

    abstract fun libroDao() : LibroDao
    abstract fun autorDao() : AutorDao
    abstract fun tagDao() : TagDao
    abstract fun tagXLibroDao() : LibroXTagDao

    companion object {
        private var INSTANCE:LibraryDatabase?=null

        fun getInstance(context: Context):LibraryDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room
                    .databaseBuilder(context,LibraryDatabase::class.java,"LibraryDB")
                    .build()
                INSTANCE= instance
                return instance
            }
        }
    }



}
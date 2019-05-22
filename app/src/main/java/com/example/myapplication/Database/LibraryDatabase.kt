package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Entities.Autor
import com.example.myapplication.Entities.Libro

@Database(entities = [Autor::class,Libro::class],version = 1,exportSchema = false)
public abstract class LibraryDatabase:RoomDatabase() {

    abstract fun libroDao() : LibroDao
    abstract fun autorDao() : AutorDao

    companion object {
        private var INSTANCE:LibraryDatabase?=null

        fun getInstance(context: Context):LibraryDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room
                    .databaseBuilder(context,LibraryDatabase::class.java,"RepoDB")
                    .build()
                INSTANCE= instance
                return instance
            }
        }
    }



}
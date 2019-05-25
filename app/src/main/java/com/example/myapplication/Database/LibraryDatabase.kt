package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.Dao.*
import com.example.myapplication.Entities.*
import com.example.myapplication.typeConvert.ListConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Autor::class,Libro::class,Tag::class,LibroXTag::class]
    ,version = 1,exportSchema = false)
abstract class LibraryDatabase:RoomDatabase() {

    abstract fun libroDao() : LibroDao
    abstract fun autorDao() : AutorDao
    abstract fun tagDao() : TagDao
    abstract fun tagXLibroDao() : LibroXTagDao

    companion object {
        private var INSTANCE:LibraryDatabase?=null

        fun getInstance(context: Context, scope: CoroutineScope):LibraryDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room
                    .databaseBuilder(context,LibraryDatabase::class.java,"LibraryDB")
                    .addCallback(WorldDataBaseCallBack(scope))
                    .build()
                INSTANCE= instance

                return instance
            }
        }

        private class WorldDataBaseCallBack(
            private val scope: CoroutineScope
        ): RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase){
                super.onOpen(db)
                INSTANCE?.let{ libraryDatabase ->
                    scope.launch(Dispatchers.IO) {
                        populateDataBase(libraryDatabase.autorDao(),libraryDatabase.libroDao(),libraryDatabase.tagDao()
                        ,libraryDatabase.tagXLibroDao())
                    }
                }
            }
        }

        suspend fun populateDataBase(autorDao: AutorDao,libroDao: LibroDao,tagDao: TagDao,libroXTagDao: LibroXTagDao){
            autorDao.deleteAuthors()
            autorDao.insert(Autor("Anstirman"))
            autorDao.insert(Autor("Javier"))
            libroDao.deleteBooks()
            libroDao.insert(Libro("123",1,"Editorial1","El libro de la selva","xxx"))
            libroDao.insert(Libro("456",2,"Editorial1","El libro de la selva 2","xxx"))
            libroDao.insert(Libro("789",1,"Editorial1","El libro de la selva 3","xxx"))
            tagDao.deleteTags()
            tagDao.insert(Tag("Fantasia"))
            tagDao.insert(Tag("Infantil"))
            tagDao.insert(Tag("Adultos"))
            libroXTagDao.deleteLibroXTag()
            libroXTagDao.insert(LibroXTag(1,"123"))
            libroXTagDao.insert(LibroXTag(2,"123"))
            libroXTagDao.insert(LibroXTag(3,"456"))
            libroXTagDao.insert(LibroXTag(2,"789"))
        }

    }



}
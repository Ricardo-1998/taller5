package com.example.myapplication.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myapplication.Entities.Libro

@Dao
interface LibroDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(libro: Libro)

    @Query("SELECT * FROM book_table")
    fun getAllBooks() : LiveData<List<Libro>>

    @Query("DELETE FROM book_table")
    fun deleteBooks()

    @Query("DELETE FROM book_table WHERE isbn Like :isbn")
    fun deleteOneBook(isbn:String)

    @Query("SELECT * FROM book_table WHERE c_nombre LIKE :referencia OR  c_autores LIKE :referencia OR  c_editorial LIKE :referencia OR  c_tag LIKE :referencia")
    fun searchBook(referencia:String) : LiveData<List<Libro>>
}
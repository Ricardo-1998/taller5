package com.example.myapplication.Dao

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

    //@Query("SELECT * FROM book_table")
    //fun getAllBooks() : LiveData<List<Libro>>

    @Query("DELETE FROM book_table")
    fun deleteBooks()

    @Query("DELETE FROM book_table WHERE isbn Like :isbn")
    fun deleteOneBook(isbn:String)

    @Query("SELECT a.isbn,a.c_nombre,a.c_autor,a.c_caratula,a.c_editorial FROM book_table a,bookXtag_table b,tag_table c WHERE a.c_nombre LIKE :referencia OR  a.c_autor LIKE :referencia OR  a.c_editorial LIKE :referencia OR (b.idTag LIKE c.id AND c.nombre LIKE :referencia AND b.isbnLibro LIKE a.isbn)")
    fun searchBook(referencia:String) : LiveData<List<Libro>>

    @Query("SELECT * FROM book_table")
    fun getAllBooks() : LiveData<List<Libro>>
}
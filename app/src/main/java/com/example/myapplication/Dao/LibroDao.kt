package com.example.myapplication.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myapplication.Entities.Libro
import com.example.myapplication.models.UnLibro

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

    @Query("SELECT c.isbn,c.c_caratula,c.c_nombre,c.c_editorial,c.c_autor,a.nombre,a.idAutor,b.isbnLibro,b.idTag,d.idTag,d.type FROM bookXtag_table b INNER JOIN book_table c ON b.isbnLibro = c.isbn INNER JOIN author_table a ON c.c_autor = a.idAutor INNER JOIN tag_table d ON d.idTag = b.idTag WHERE c.c_nombre LIKE :referencia OR  c.c_autor LIKE :referencia OR  c.c_editorial LIKE :referencia OR a.nombre LIKE :referencia OR d.type LIKE :referencia")
    fun searchBook(referencia:String) : LiveData<List<UnLibro>>

    @Query("SELECT c.isbn,c.c_caratula,c.c_nombre,c.c_editorial,c.c_autor,a.nombre,a.idAutor,b.isbnLibro,b.idTag,d.idTag,d.type FROM bookXtag_table b INNER JOIN book_table c ON b.isbnLibro = c.isbn INNER JOIN author_table a ON c.c_autor = a.idAutor INNER JOIN tag_table d ON d.idTag = b.idTag")
    fun getAllBooksUnLibro() : LiveData<List<UnLibro>>

    @Query("SELECT c.isbn,c.c_caratula,c.c_nombre,c.c_editorial,c.c_autor,a.nombre,a.idAutor,b.isbnLibro,b.idTag,d.idTag,d.type FROM bookXtag_table b INNER JOIN book_table c ON b.isbnLibro = c.isbn INNER JOIN author_table a ON c.c_autor = a.idAutor INNER JOIN tag_table d ON d.idTag = b.idTag WHERE c.isbn = :isbn")
    fun getOneBook(isbn:String): LiveData<List<UnLibro>>

    @Query("SELECT a.isbn,a.c_nombre,a.c_autor,a.c_caratula,a.c_editorial FROM book_table a,bookXtag_table b,tag_table c WHERE a.c_nombre LIKE :referencia OR  a.c_autor LIKE :referencia OR  a.c_editorial LIKE :referencia OR (b.idTag LIKE c.idTag AND c.type LIKE :referencia AND b.isbnLibro LIKE a.isbn)")
    fun searchBook2(referencia:String) : LiveData<List<Libro>>

    //@Query("SELECT * FROM book_table")
    //fun getAllBooks() : LiveData<List<Libro>>*/

}
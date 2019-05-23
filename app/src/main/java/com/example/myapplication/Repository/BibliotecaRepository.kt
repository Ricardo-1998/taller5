package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.Database.AutorDao
import com.example.myapplication.Database.LibroDao
import com.example.myapplication.Entities.Autor
import com.example.myapplication.Entities.Libro

class BibliotecaRepository(private val autorDao : AutorDao, private val libroDao : LibroDao){

    //LIBRO DAO METODOS
    fun allBooks() : LiveData<List<Libro>> = libroDao.getAllBooks()

    @WorkerThread
    suspend fun insertBook(libro : Libro){
        libroDao.insert(libro)
    }

    //fun deleteLibro() = libroDao.deleteLibro()

    //@WorkerThread
    //fun buscarLibro(referencia : String)=  libroDao.buscarLibro(referencia)

    //AUTOR DAO METODOS

    fun getAllAuthors() : LiveData<List<Autor>> = autorDao.getAllAuthors()

    @WorkerThread
    suspend fun insertAuthor(autor : Autor){
        autorDao.insert(autor)
    }
}
package com.example.myapplication.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.LibraryDatabase
import com.example.myapplication.Entities.Autor
import com.example.myapplication.Entities.Libro
import com.example.myapplication.Repository.BibliotecaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BibliotecaViewModel(application: Application) : AndroidViewModel(application) {

    private val bibliotecaRepository: BibliotecaRepository


    init {
        val bookDao = LibraryDatabase.getInstance(application).libroDao()
        val authorDao = LibraryDatabase.getInstance(application).autorDao()

        bibliotecaRepository = BibliotecaRepository(authorDao,bookDao)
    }

    fun allBooks() : LiveData<List<Libro>> = bibliotecaRepository.allBooks()

    fun insertBook(libro : Libro) = viewModelScope.launch(Dispatchers.IO){bibliotecaRepository.insertBook(libro)}

    fun searchBook(referencia:String) = bibliotecaRepository.searchBook(referencia)
    //fun deleteBook() =  bibliotecaRepository.deleteLibro()

   // fun buscarLibro(referencia : String) = viewModelScope.launch(Dispatchers.IO){bibliotecaRepository.buscarLibro(referencia)}




    fun getAllAuthors() : LiveData<List<Autor>> = bibliotecaRepository.getAllAuthors()

    fun insertAuthor(autor: Autor) = viewModelScope.launch(Dispatchers.IO) {bibliotecaRepository.insertAuthor(autor)}

    //fun deleteAuthors() = authorRepository.deleteAuthors()



}
package com.example.myapplication.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Entities.Autor
import com.example.myapplication.Entities.Libro
import com.example.myapplication.Repository.AuthorRepository
import com.example.myapplication.Repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BibliotecaViewModel(application: Application) : AndroidViewModel(application) {

    private val bookRepository:  BookRepository
    private val authorRepository:  AuthorRepository

    init {
        val repoDao = RoomDB.getInstance(application).repoDao()
        bookRepository = BookRepository(repoDao)
        authorRepository = AuthorRepository(repoDao)
    }

    fun allBooks() : LiveData<List<Libro>> = bookRepository.allBooks()

    fun insertBook(libro : Libro) = viewModelScope.launch(Dispatchers.IO){bookRepository.insertBook(libro)}

    fun deleteBook() =  bookRepository.deleteLibro()

    fun buscarLibro(referencia : String) = viewModelScope.launch(Dispatchers.IO){bookRepository.buscarLibro(referencia)}

    fun buscarUnLibro(isbn : String) = viewModelScope.launch(Dispatchers.IO){bookRepository.buscarUnLibro(isbn)}



    fun getAllAuthors() : LiveData<List<Autor>> = authorRepository.getAllAuthors()

    fun insertAuthor(autor: Autor) = viewModelScope.launch(Dispatchers.IO) {authorRepository.insertAuthor(autor)}

    fun deleteAuthors() = authorRepository.deleteAuthors()



}
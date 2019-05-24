package com.example.myapplication.Fragment

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.bookAdapter
import com.example.myapplication.Entities.Libro
import com.example.myapplication.R
import kotlinx.android.synthetic.main.books_fragment_list.view.*
import java.lang.ClassCastException


class booksFragment: Fragment() {

    var listenerTool :  clickListener? = null
    lateinit var bookAdapter: bookAdapter

    companion object {
        @JvmStatic
        fun newInstance() =
            booksFragment().apply {

            }
    }

    interface clickListener{
        fun itemClick(book: Libro)
        fun delete(book: Libro)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.books_fragment_list, container, false)
        initRecyclerView(view)
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is clickListener) {
            listenerTool = context
        } else {
            throw ClassCastException("Se necesita una implementacion de  la interfaz")
        }
    }
    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    fun updateAdapter(book: List<Libro>){
        this.bookAdapter.setBook(book)
    }

    fun initRecyclerView(container:View){
        bookAdapter = bookAdapter({ book:Libro->listenerTool?.itemClick(book)},
            { book:Libro ->listenerTool?.delete(book)})
        container.rv_book_list.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this.context,2)
            adapter = bookAdapter
        }
    }

}
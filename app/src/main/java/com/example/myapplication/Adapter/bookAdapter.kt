package com.example.myapplication.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Entities.Libro
import com.example.myapplication.R
import com.example.myapplication.models.UnLibro
import kotlinx.android.synthetic.main.activity_book_info.view.*
import kotlinx.android.synthetic.main.libro_item.view.*

class bookAdapter(
    private val clickListener: (Libro) -> Unit,
    private val deleteLibro: (Libro) -> Unit
) : RecyclerView.Adapter<bookAdapter.ViewHolder>() {

    private var books: List<Libro> = emptyList()
    private var unLibro: List<UnLibro> = emptyList()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Libro,unLibro: List<UnLibro>, clickListener: (Libro) -> Unit, deleteLibro: (Libro) -> Unit) = with(itemView){
            var string = ""
            Log.wtf("NO JODAS","${unLibro.isNullOrEmpty()}")
            for(a in unLibro){
                if(a.idAutor == item.autores){
                    string = a.nombre
                }

            }

            tv_name_frag.text = item.nombre
            tv_isbn_frag.text = item.isbn
            tv_autores_frag.text = string
            delete_book.setOnClickListener { deleteLibro(item)}
            this.setOnClickListener { clickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.libro_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(books[position],unLibro, clickListener, deleteLibro)

    fun setBook(libros: List<Libro>, unLibro: List<UnLibro>){
        this.books = libros
        this.unLibro = unLibro
        notifyDataSetChanged()
    }


}
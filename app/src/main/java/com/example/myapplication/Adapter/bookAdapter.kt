package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Entities.Libro
import com.example.myapplication.R
import kotlinx.android.synthetic.main.libro_item.view.*

class bookAdapter(
    private val clickListener: (Libro) -> Unit
) : RecyclerView.Adapter<bookAdapter.ViewHolder>() {

    private var books: List<Libro> = emptyList()
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Libro, clickListener: (Libro) -> Unit) = with(itemView){
            tv_name_frag.text = item.nombre
            tv_author_frag.text = item.tags
            tv_tags_frag.text = item.autores
            this.setOnClickListener { clickListener(item) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.libro_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(books[position], clickListener)

    fun setBook(libros: List<Libro>){
        this.books = libros
        notifyDataSetChanged()
    }


}
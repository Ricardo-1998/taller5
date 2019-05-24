package com.example.myapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.Entities.Libro
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_book_info.*

class BookInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)
        val author = intent.extras.getString(addActivity.EXTRA_AUTHOR)
        val tag = intent.extras.getString(addActivity.EXTRA_TAG)
        val name = intent.extras.getString(addActivity.EXTRA_NAME)
        val editorial = intent.extras.getString(addActivity.EXTRA_EDITORIAL)
        val isbn = intent.extras.getString(addActivity.EXTRA_ISBN)
        val cartelera = intent.extras.getString(addActivity.EXTRA_CARTELERA)

        Glide.with(this)
            .load(cartelera)
            .placeholder(R.drawable.ic_launcher_background)
            .into(iv_cartelera)
        tv_name.text = name
        tv_isbn.text = isbn
        tv_editorial.text = editorial
        tv_tags.text = tag
        tv_author.text = author
    }

}

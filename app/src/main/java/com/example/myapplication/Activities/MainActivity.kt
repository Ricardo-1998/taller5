package com.example.myapplication.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.Entities.Autor
import com.example.myapplication.Entities.Libro
import com.example.myapplication.Entities.LibroXTag
import com.example.myapplication.Entities.Tag
import com.example.myapplication.Fragment.booksFragment
import com.example.myapplication.R
import com.example.myapplication.ViewModels.BibliotecaViewModel
import com.example.myapplication.models.UnLibro
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), booksFragment.clickListener  {

    private val addActivityRequestCode = 1
    lateinit var viewModel: BibliotecaViewModel
    private lateinit var fragment: booksFragment

    override fun itemClick(book: Libro) {
        var StringTag = ""
        var autorString = ""
        var unL = viewModel.getAllBooksUnLibro().value
        var alltag = viewModel.getTags().value
        var alltagxvalue = viewModel.getAllLibroXTag().value
        startActivity(
            Intent(this, BookInfoActivity::class.java)
                .putExtra(addActivity.EXTRA_NAME, book.nombre)
                .putExtra(addActivity.EXTRA_ISBN, book.isbn)
                .putExtra(addActivity.EXTRA_CARTELERA, book.caratula)
                .putExtra(addActivity.EXTRA_EDITORIAL, book.editorial)
                .putExtra(addActivity.EXTRA_AUTHOR, autorString)
                .putExtra(addActivity.EXTRA_TAG, StringTag)
        )
    }

    override fun delete(book: Libro) {
        viewModel.deleteOneBook(book.isbn)
        Toast.makeText(this,getString(R.string.remove_book),Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        viewModel = ViewModelProviders.of(this).get(BibliotecaViewModel::class.java)
        ObservarAllBook()

        btn_refresh.setOnClickListener {
            ObservarAllBook()
        }
        add_book.setOnClickListener {
            val intent = Intent(this@MainActivity, addActivity::class.java)
            startActivityForResult(intent, addActivityRequestCode)
        }
        delete_book.setOnClickListener {
            viewModel.deleteBooks()
            Toast.makeText(this,getString(R.string.remove_all_books),Toast.LENGTH_LONG).show()
        }
        btn_search.setOnClickListener {
            viewModel.getAllBooks().observe(this, Observer { unLibro ->
                viewModel.searchBook(et_search.text.toString()).observe(this, Observer { books ->
                    // Update the cached copy of the words in the adapter.
                    books?.let {
                        if(unLibro!=null) {
                            fragment.updateAdapter(unLibro, it)
                        }
                    }
                })
            })
            et_search.text.clear()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == addActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val isbn = data.getStringExtra(addActivity.EXTRA_ISBN)
                val arrayTags = data.getStringArrayExtra(addActivity.EXTRA_TAG)
                Log.wtf("NO JODAS","${arrayTags.isEmpty()}")
                val autorname = data.getStringExtra(addActivity.EXTRA_AUTHOR)
                insertarTag(arrayTags)
                insertAutor(autorname)
                val idAutor = getIdAutor(autorname)
                val book = Libro(isbn, idAutor,
                    data.getStringExtra(addActivity.EXTRA_EDITORIAL),
                    data.getStringExtra(addActivity.EXTRA_NAME),
                    data.getStringExtra(addActivity.EXTRA_CARTELERA))
                viewModel.insertBook(book)

                insertarLibroXTag(arrayTags,isbn)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
        ObservarAllBook()
    }

    private fun initFragment(){
        fragment = booksFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_content, fragment).commit()
    }

    private fun ObservarAllBook(){
        viewModel.getAllBooksUnLibro().observe(this, Observer { unLibro ->
            if (unLibro != null){
                viewModel.getAllBooks().observe(this, Observer { books ->
                    books?.let {
                        if (it != null) {
                            fragment.updateAdapter(it,unLibro)
                        }
                    }
                })
            }
        })

    }

    private fun insertarTag(tags:Array<String>){
        for (a in tags){
            if(viewModel.getTags().value.isNullOrEmpty()) {
                viewModel.insertTag(Tag(0,a))
            }else{
                viewModel.insertTag(Tag(viewModel.getTags().value!!.size,a))
                Log.wtf("DLSMADL","melwqmlmdl")
            }
        }
    }

    private fun insertarLibroXTag(tags: Array<String>,isbn:String){
        var allTags = viewModel.getTags().value
        if(allTags != null){
            for (a in allTags!!){
                for (b in tags) {
                    if(a.type == b) {
                        if (viewModel.getAllLibroXTag().value.isNullOrEmpty()) {
                            viewModel.insertLibroXTag(LibroXTag(0,a.idTag, isbn))
                        }else{
                            viewModel.insertLibroXTag(LibroXTag(viewModel.getAllLibroXTag().value!!.size,a.idTag, isbn))
                        }
                    }
                }
            }
        }

    }

    private fun insertAutor(nombre:String){
        if(viewModel.getAllAuthors().value.isNullOrEmpty()){
                viewModel.insertAuthor(Autor(0,nombre))
        }else{
            viewModel.insertAuthor(Autor(viewModel.getAllAuthors().value!!.size,nombre))
        }
    }

    private fun getIdAutor(nombre:String): Int{
        var id = 0
        viewModel.getAllAuthors().observe(this, Observer {
            for (a in it){
                if(a.nombre == nombre){
                    id = a.idAutor
                }
            }
        })
        return id
    }
}



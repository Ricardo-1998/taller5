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
    private lateinit var unLibro:List<UnLibro>

    override fun itemClick(book: Libro, unLibro: List<UnLibro>) {
        var StringTag = ""
        var autorString = ""
        var cont= 0
        for( a in unLibro!!){
            if(book.isbn==a.isbn){
                if(cont == 0) {
                    autorString = a.nombre
                    StringTag = a.type
                }
                else{
                    StringTag += ",${a.type}"
                    autorString = a.nombre
                }
                cont++
            }
        }
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
            viewModel.getAllBooksUnLibro().observe(this, Observer { unLibro ->
                viewModel.searchBook2(et_search.text.toString()).observe(this, Observer { books ->
                    // Update the cached copy of the words in the adapter.
                    books?.let {
                        if (unLibro != null) {
                            fragment.updateAdapter(it,unLibro)
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
                val autorname = data.getStringExtra(addActivity.EXTRA_AUTHOR)
                //Insert Tag
                var id = 0
                viewModel.getTags().observe(this, Observer { listTag ->
                    if (listTag == null) {
                        for (a in arrayTags) {
                            viewModel.insertTag(Tag(a))
                        }
                    }else {
                        for (b in arrayTags) {
                            if (!TagCan(b,listTag)) {
                                viewModel.insertTag(Tag(b))
                            }
                        }
                    }
                    //Insert Author
                    viewModel.getAllAuthors().observe(this, Observer{
                        if(it == null){
                            viewModel.insertAuthor(Autor(autorname))
                        }
                        else{
                            if(!AutorCan(autorname,it)){
                                viewModel.insertAuthor(Autor(autorname))
                            }
                            for (a in it){
                                if(a.nombre == autorname){
                                    id = a.idAutor
                                }
                            }
                        }
                        //Insert Book
                        val book = Libro(isbn, id,
                            data.getStringExtra(addActivity.EXTRA_EDITORIAL),
                            data.getStringExtra(addActivity.EXTRA_NAME),
                            data.getStringExtra(addActivity.EXTRA_CARTELERA))
                        viewModel.insertBook(book)
                        //Insert Book x Tags
                        for (a in listTag) {
                            for (b in arrayTags) {
                                if (b == a.type) {
                                    viewModel.insertLibroXTag(LibroXTag(a.idTag, isbn))
                                }
                            }
                        }
                    })
                })
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
                            this.unLibro = unLibro
                            fragment.updateAdapter(it,unLibro)
                        }
                    }
                })
            }
        })

    }

    private fun AutorCan(nombre:String, array:List<Autor>):Boolean{
        for(a in array){
            if(a.nombre == nombre){
                return true
            }
        }
        return false
    }

    private fun TagCan(nombre:String, array:List<Tag>):Boolean{
        for(a in array){
            if(a.type == nombre){
                return true
            }
        }
        return false
    }
}



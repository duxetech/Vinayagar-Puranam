package com.duxetech.vinayakarpuranam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.io.FileNotFoundException
import java.util.zip.Inflater

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var spinner1 : Spinner? = null
    var contentView : TextView? = null
    var chapter = 0
    var txt = ""

    val defText = "*****ஓம் கம் கணபதியே நம*****\n"

    lateinit var adapter2 : ArrayAdapter<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFiles(chapter)

        spinner1 = findViewById(R.id.spinner1)
        contentView = findViewById(R.id.contentView)

        adapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, chapters)

        spinner1!!.onItemSelectedListener = this


        spinner1!!.adapter = adapter2

        contentView!!.movementMethod = ScrollingMovementMethod()

        spinner1!!.onItemSelectedListener

        contentView!!.text = txt

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    var size = 16F
    var aboutSize = 20F

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item!!.itemId) {

            R.id.increaseFont -> {
                if (size < 25)
                size += 1F
                contentView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP,size)
                true
            }
            R.id.decreaseFont -> {
                if (size > 11)
                size -= 1F
                contentView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP,size)
                true
            }
            R.id.about -> {


                startActivity(Intent(this,AboutActivity::class.java))
                true
            }
            R.id.share -> {
                var intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,"விநாயகர் புராணம் app https://play.google.com/store/apps/details?id=vinayakar.puranam&hl=en_IN")
                startActivity(Intent.createChooser(intent,
                "Share via"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
     }
    private fun loadFiles(chapter : Int){

        val file = "$chapter.txt"

        try {
            txt = application.assets.open(file).bufferedReader().
            use {
                it.readText()
            }

            //      Log.i("om",txt)

        } catch (e:FileNotFoundException) {

        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        loadFiles(position)

        contentView!!.text = defText+
                             txt+
                             "\n" + defText
        contentView!!.scrollTo(0,0)

    }





}

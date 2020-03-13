package com.duxetech.vinayakarpuranam

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

    lateinit var adapter1 : ArrayAdapter<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFiles(chapter)

        spinner1 = findViewById(R.id.spinner1)
        contentView = findViewById(R.id.contentView)

        adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item,chapters)

        spinner1!!.setOnItemSelectedListener(this)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinner1!!.adapter = adapter1

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
                Toast.makeText(this,size.toString(),Toast.LENGTH_SHORT).show()

                true
            }
            R.id.decreaseFont -> {
                if (size > 11)
                size -= 1F
                contentView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP,size)
                Toast.makeText(this,size.toString(),Toast.LENGTH_SHORT).show()
                true
            }
            R.id.about -> {
                contentView!!.text = "developed by  duxetech.com. \n" +
                        "Android  & iOS app development \n"+
                        "Contact : \n" +
                        "karthikriches@gmail.com"
                contentView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP,aboutSize)
               // spinner1!!.visibility = View.GONE
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
        if (aboutSize!=size)
            contentView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP,size)

        contentView!!.text = txt
        contentView!!.text = defText+
                             contentView!!.text+
                             "\n" + defText
        contentView!!.scrollTo(0,0)

    }





}

package com.duxetech.vinayakarpuranam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.io.FileNotFoundException
import java.util.zip.Inflater

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var spinner1 : Spinner? = null
    var contentView : TextView? = null
    var book = 0
    var chapter = 0
    var txt = ""

    var booktitles = arrayOf("திருஞானசம்பந்தர் தேவாரம் 1",
        "திருஞானசம்பந்தர் தேவாரம் 2",
        "திருஞானசம்பந்தர் தேவாரம் 3",
        "திருநாவுக்கரசர் தேவாரம் 1",
        "திருநாவுக்கரசர் தேவாரம் 2",
        "திருநாவுக்கரசர் தேவாரம் 3",
        "சுந்தரர் தேவாரம்",
        "மாணிக்கவாசகர் திருவாசகம்")

    val titles = arrayOf("முதல் திருமுறை","இரண்டாம் திருமுறை", "மூன்றாம் திருமுறை", "நான்காம் திருமுறை", "ஐந்தாம் திருமுறை", "ஆறாம் திருமுறை", "ஏழாம் திருமுறை", "எட்டாம் திருமுறை")


    val defText = "தென்னாடுடைய சிவனே போற்றி" +
            "எந்நாட்டவர்க்கும் இறைவா போற்றி!"


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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item!!.itemId) {
            R.id.font -> {
                Toast.makeText(this, "font", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.about -> {
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show()
                contentView!!.text = "karthikriches@gmail.com"
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
        contentView!!.text = txt
        contentView!!.scrollTo(0,0)

    }





}

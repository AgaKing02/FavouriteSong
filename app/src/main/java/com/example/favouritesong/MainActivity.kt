package com.example.favouritesong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.File
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var lyrics: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUp()


        var counter = 0;

        button.setOnClickListener {
            if(counter==0)
                textView.text=""
            try {
                textView.append(lyrics.get(counter) + "\n")
            } catch (e: Exception) {
                button.text = "Finished"
            } finally {
                counter++;
            }

        }

    }

    fun readFileAsLinesUsingBufferedReader(fileName: String): List<String> {
        return File(fileName).bufferedReader().readLines();
    }

    fun setUp() {
        button = findViewById(R.id.start_button) as Button
        textView = findViewById(R.id.start) as TextView

        var string: String? = ""
        try {
            val inputStream: InputStream = assets.open("Lyrics.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            string = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (string != null) {
            lyrics = string.lines()
        }
    }
}
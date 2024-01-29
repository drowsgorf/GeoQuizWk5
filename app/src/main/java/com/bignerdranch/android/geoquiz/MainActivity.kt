package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

//        trueButton.setOnClickListener {
//            val snackbar = Snackbar.make(
//                it,
//                R.string.correct_toast,
//                Snackbar.LENGTH_LONG
//            )
//            snackbar.show()
//        }
//        falseButton.setOnClickListener {
//            val snackbar = Snackbar.make(
//                it,
//                R.string.incorrect_toast,
//                Snackbar.LENGTH_LONG
//            )
//            snackbar.show()
//        }

        trueButton.setOnClickListener { view: View ->
            Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
        falseButton.setOnClickListener { view: View ->
            Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
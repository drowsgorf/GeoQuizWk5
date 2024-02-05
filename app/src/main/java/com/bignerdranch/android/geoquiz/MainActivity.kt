package com.bignerdranch.android.geoquiz

import Question
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    EXERCISE 2 -> ADDED isAnswered = false
    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, false, false),
        Question(R.string.question_asia, true, false)
    )
//

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        }
        else {
            R.string.incorrect_toast
        }

//        EXERCISE 2
        questionBank[currentIndex].isAnswered = true
        disableAnswerBtns()
//

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

//        EXERCISE 3
        if (userAnswer == correctAnswer) {
            correctCount ++
        }
        answeredCount ++

        checkForCompletion(userAnswer, correctAnswer)
//
    }

//    EXERCISE 2
    private fun disableOrEnableBtns() {
        if (questionBank[currentIndex].isAnswered) {
            disableAnswerBtns()
        }
        else {
            enableAnswerBtns()
        }
    }

    private fun disableAnswerBtns() {
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false
    }

    private fun enableAnswerBtns() {
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }
//

//    EXERCISE 3
    private var answeredCount: Int = 0
    private var correctCount: Int = 0
    private var score: Double = 0.0
    private var scoreString: String = "Your score is: "

    private fun checkForCompletion(userAnswer: Boolean, correctAnswer: Boolean) {
        if (answeredCount == questionBank.size) {
            score = correctCount/answeredCount.toDouble() * 100
            scoreString += score.toString()
            scoreString += "%"

            Toast.makeText(this, scoreString, Toast.LENGTH_LONG).show()

            resetQuiz()
        }
    }

    private fun resetQuiz() {
        answeredCount = 0
        correctCount = 0
        score = 0.0
        scoreString = "You have finished! Your score is: "
        currentIndex = 0

        for(q in questionBank) {
            q.isAnswered = false
        }

        enableAnswerBtns()
        updateQuestion()
    }
//

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.questionTextView.setOnClickListener { view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()

//            EXERCISE 2
            disableOrEnableBtns()
//
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()

//            EXERCISE 2
            disableOrEnableBtns()
//
        }

        binding.previousButton.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questionBank.size - 1
            }
            else {
                currentIndex = (currentIndex - 1) % questionBank.size
            }
            updateQuestion()

//            EXERCISE 2
            disableOrEnableBtns()
//
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onStart() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStart() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onStart() called")
    }
}
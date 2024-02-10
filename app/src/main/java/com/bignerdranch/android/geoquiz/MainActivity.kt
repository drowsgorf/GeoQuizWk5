package com.bignerdranch.android.geoquiz

import Question
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        }
        else {
            R.string.incorrect_toast
        }

        quizViewModel.questionBank[quizViewModel.currentIndex].isAnswered = true
        disableAnswerBtns()

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        if (userAnswer == correctAnswer) {
            correctCount ++
        }
        answeredCount ++

        checkForCompletion(userAnswer, correctAnswer)
    }

    private fun disableOrEnableBtns() {
        if (quizViewModel.questionBank[quizViewModel.currentIndex].isAnswered) {
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

    private var answeredCount: Int = 0
    private var correctCount: Int = 0
    private var score: Double = 0.0
    private var scoreString: String = "Your score is: "

    private fun checkForCompletion(userAnswer: Boolean, correctAnswer: Boolean) {
        if (answeredCount == quizViewModel.questionBank.size) {
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
        quizViewModel.currentIndex = 0

        for(q in quizViewModel.questionBank) {
            q.isAnswered = false
        }

        enableAnswerBtns()
        updateQuestion()
    }
//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.questionTextView.setOnClickListener { view: View ->
            quizViewModel.moveToNext()
            updateQuestion()

            disableOrEnableBtns()
        }

        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()

            disableOrEnableBtns()
        }

        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()

            updateQuestion()

            disableOrEnableBtns()
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
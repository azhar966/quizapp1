package com.zebdul.quizapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var questionsList: ArrayList<QuestionModel>
    private var index: Int = 0
    lateinit var questionModel: QuestionModel

    private var correctAnswerCount: Int = 0
    private var wrongAnswerCount: Int = 0

    lateinit var countDown: TextView
    lateinit var questions: TextView
    lateinit var option1: TextView
    lateinit var option2: TextView
    lateinit var option3: TextView
    lateinit var option4: TextView

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        countDown = findViewById(R.id.countDown)
        questions = findViewById(R.id.questions)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)


        questionsList = ArrayList()
        questionsList.add(
            QuestionModel(
                "Who developed Kotlin?", "IBM", "GOOGLE", "JetBrains", "Microsoft", "JetBrains"
            )
        )

        questionsList.add(
            QuestionModel(
                "Which extension is responsible to save Kotlin files ?",
                "kot",
                ".android",
                ".src",
                ".kt or .kts",
                ".kt or .kts"
            )
        )

        questionsList.add(
            QuestionModel(
                "How to do a multi-line comment in Kotlin language?",
                "Forward Slace",
                "Backword Slace",
                "Backword Slace + Multiply sign",
                "None of the above",
                "Backword Slace + Multiply sign"
            )
        )

        questionsList.add(
            QuestionModel(
                ".The twvo types of constructors in Kotlin are?",
                "Primary and Secondary constructor",
                "First and the second constructor",
                "Constant and Parameterized constructor",
                "None of these",
                "Primary and Secondary constructor"
            )
        )

        questionsList.add(
            QuestionModel(
                "What handles null exceptions in Kotlin?",
                "Sealed classes",
                "Lambda functions",
                "The Kotlin extension",
                "Elvis operator",
                "Elvis operator"
            )
        )

        questionsList.add(
            QuestionModel(
                "The correct function to get the length of a string in Kotlin language is ?",
                "str.length",
                "string(length)",
                "lengthof(str)",
                "None of these",
                "str.length"
            )
        )

        questionsList.add(
            QuestionModel(
                " The function to print a line in Kotlin is ?",
                "Printline()",
                "println0",
                "print)",
                " option (b) and (c)",
                " option (b) and (c)"
            )
        )

        questionsList.add(
            QuestionModel(
                "Under which license Kotlin was developed?", "1.1", "1.5", "2.0", "2.1", "2.0"
            )
        )

        questionsList.add(
            QuestionModel(
                " In Kotlin, the default visibility modifier is ?",
                "sealed",
                "public",
                "protected",
                "private",
                "public"
            )
        )

        questionsList.add(
            QuestionModel(
                "The functions in Kotlin can be divided into how many types?",
                "5",
                "4",
                "3",
                "2",
                "2"
            )
        )

        questionsList.shuffle()
        questionModel = questionsList[index]

        setAllQuestions()
        countdown()


    }

    private fun countdown() {
        var duration: Long = TimeUnit.SECONDS.toMillis(8)

        object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration: String = String.format(
                    Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)

                    )
                )
                countDown.text = sDuration
            }

            override fun onFinish() {
                index++
                if (index < questionsList.size) {
                    questionModel = questionsList[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()
                } else {
                    gameResult()
                }
            }
        }.start()
    }

    private fun correctAns(option: TextView) {
        option.background = getDrawable(R.drawable.right_bg)
        correctAnswerCount++
    }

    private fun wrongAns(option: TextView) {
        option.background = getDrawable(R.drawable.wrong_bg)
        wrongAnswerCount++
    }

    private fun gameResult() {
        var intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correct", correctAnswerCount.toString())
        intent.putExtra("total", questionsList.size.toString())
        startActivity(intent)
    }


    private fun setAllQuestions() {
        questions.text = questionModel.question
        option1.text = questionModel.option1
        option2.text = questionModel.option2
        option3.text = questionModel.option3
        option4.text = questionModel.option4
    }

    private fun enableButton() {
        option1.isClickable = true
        option2.isClickable = true
        option3.isClickable = true
        option4.isClickable = true
    }

    private fun disableButton() {
        option1.isClickable = false
        option2.isClickable = false
        option3.isClickable = false
        option4.isClickable = false

    }

    private fun resetBackground() {
        option1.background = resources.getDrawable(R.drawable.option_bg)
        option2.background = resources.getDrawable(R.drawable.option_bg)
        option3.background = resources.getDrawable(R.drawable.option_bg)
        option4.background = resources.getDrawable(R.drawable.option_bg)
    }


    fun option1Clicked(view: View) {
        disableButton()
        if (questionModel.option1 == questionModel.answer) {
            option1.background = resources.getDrawable(R.drawable.right_bg)
            correctAns(option1)
        } else {
            wrongAns(option1)
        }

    }


    fun option2Clicked(view: View) {
        disableButton()
        if (questionModel.option2 == questionModel.answer) {
            option2.background = resources.getDrawable(R.drawable.right_bg)
            correctAns(option2)
        } else {
            wrongAns(option2)
        }
    }


    fun option3Clicked(view: View) {
        disableButton()
        if (questionModel.option3 == questionModel.answer) {
            option3.background = resources.getDrawable(R.drawable.right_bg)
            correctAns(option3)
        } else {
            wrongAns(option3)
        }
    }

    fun option4Clicked(view: View) {
        disableButton()
        if (questionModel.option4 == questionModel.answer) {
            option4.background = resources.getDrawable(R.drawable.right_bg)
            correctAns(option4)
        } else {
            wrongAns(option4)
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finish()
        } else {
            backToast = Toast.makeText(baseContext, "Double press to quit game", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}
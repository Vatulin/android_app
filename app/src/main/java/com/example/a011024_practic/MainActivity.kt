package com.example.a011024_practic

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Инициализация для первого экрана
        val change_color_button = findViewById<Button>(R.id.change_color_button)
        val first_layout = findViewById<LinearLayout>(R.id.first_layout)
        var state_color_first = "yellow"
        var third_layout_text = findViewById<TextView>(R.id.third_layout_text)

        //Инициализация для второго экрана
        val second_layout = findViewById<LinearLayout>(R.id.second_layout)
        val edit_text = findViewById<EditText>(R.id.edit_text)

        //Инициализация для третьего экрана
        val third_layout = findViewById<LinearLayout>(R.id.third_layout)
        var state_color_third = "blue"

        //Инициализация для четвертого экрана
        val fourth_layout = findViewById<LinearLayout>(R.id.fourth_layout)
        var mem = findViewById<ImageView>(R.id.mem)
        val raccoon = findViewById<TextView>(R.id.enot)

        change_color_button.setOnClickListener {
            if (state_color_first.equals("yellow")) {
                state_color_first = "red"
                first_layout.background = AppCompatResources.getDrawable(this, R.color.red)
            }
            else {
                state_color_first = "yellow"
                first_layout.background = AppCompatResources.getDrawable(this, R.color.yellow)
            }
        }

        fun hide_keyboard(editText: EditText) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }

        edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.toString()?.lowercase().equals("samsung")) {
                    third_layout_text.text = getString(R.string.done)
                    edit_text.setText("")
                    hide_keyboard(edit_text)
                    second_layout.background = AppCompatResources.getDrawable(this@MainActivity, R.color.white_green)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        third_layout.setOnLongClickListener {
            if (state_color_third.equals("blue")) {
                state_color_third = "green"
                third_layout.background = AppCompatResources.getDrawable(this, R.color.green)
            }
            else {
                state_color_third = "blue"
                third_layout.background = AppCompatResources.getDrawable(this, R.color.blue)
            }
            true
        }


        //Обработка дабл клика
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (mem.alpha == 1f) {
                    mem.alpha = 0f
                }
                else {
                    mem.alpha = 1f
                }
                return true
            }
        })

        fourth_layout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }
}
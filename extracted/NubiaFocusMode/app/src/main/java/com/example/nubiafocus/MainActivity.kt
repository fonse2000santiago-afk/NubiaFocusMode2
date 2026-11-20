package com.example.nubiafocus

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Typeface
import android.graphics.Color
import java.util.Locale

class MainActivity : Activity() {
    private lateinit var timerText: TextView
    private lateinit var statusText: TextView
    private var timer: CountDownTimer? = null
    private var remainingMs = 25L * 60L * 1000L
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildScreen()
        updateTimer()
    }

    private fun buildScreen() {
        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 36, 28, 36)
            setBackgroundColor(Color.rgb(16, 22, 34))
        }

        val heading = TextView(this).apply {
            text = "FOCUS MODE"
            textSize = 25f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }
        page.addView(heading, matchWrap())

        statusText = TextView(this).apply {
            text = "Ready for a focus session"
            textSize = 15f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 10)
        }
        page.addView(statusText, matchWrap())

        timerText = TextView(this).apply {
            textSize = 58f
            setTextColor(Color.WHITE)
            typeface = Typeface.MONOSPACE
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 28)
        }
        page.addView(timerText, matchWrap())

        val start = makeButton("Start 25-minute focus") { startFocus() }
        page.addView(start, matchWrap())

        val reset = makeButton("Reset timer") { resetTimer() }
        page.addView(reset, matchWrap())

        val note = TextView(this).apply {
            text = "This starter version provides a focus timer only. It does not yet block other apps."
            textSize = 13f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 30, 0, 0)
        }
        page.addView(note, matchWrap())

        setContentView(page)
    }

    private fun makeButton(label: String, action: () -> Unit): Button =
        Button(this).apply {
            text = label
            isAllCaps = false
            setOnClickListener { action() }
        }

    private fun matchWrap() = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply { bottomMargin = 10 }

    private fun startFocus() {
        if (running) {
            timer?.cancel()
            running = false
            statusText.text = "Paused"
            return
        }
        if (remainingMs <= 0L) remainingMs = 25L * 60L * 1000L
        running = true
        statusText.text = "Focus session in progress"
        timer = object : CountDownTimer(remainingMs, 1000L) {
            override fun onTick(ms: Long) {
                remainingMs = ms
                updateTimer()
            }
            override fun onFinish() {
                remainingMs = 0L
                running = false
                updateTimer()
                statusText.text = "Session complete. Well done!"
            }
        }.start()
    }

    private fun resetTimer() {
        timer?.cancel()
        running = false
        remainingMs = 25L * 60L * 1000L
        statusText.text = "Ready for a focus session"
        updateTimer()
    }

    private fun updateTimer() {
        val totalSeconds = remainingMs / 1000L
        val minutes = totalSeconds / 60L
        val seconds = totalSeconds % 60L
        timerText.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}

package com.jaime.threesec

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import androidx.appcompat.widget.AppCompatButton

const val TIME = 3000

class MainActivity : AppCompatActivity() {


    lateinit var chronometer: Chronometer
    lateinit var button: AppCompatButton

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
        setUpListeners()

        mediaPlayer = MediaPlayer.create(baseContext, R.raw.wrong_answer)

    }

    // Check what the remaining time of the count down on negative numbers
    private fun isEndOfCountDown(): Boolean {
        val base = SystemClock.elapsedRealtime() - chronometer.base
        return base > 0
    }

    //Starts countdown from the settled TIME value
    private fun startCountDown() {
        val base = SystemClock.elapsedRealtime() + TIME
        chronometer.base = base
        chronometer.start()
    }

    private fun stopCountDown() {
        chronometer.stop()
    }

    private fun reset() {
        chronometer.base = SystemClock.elapsedRealtime()
    }

    private fun setUpViews() {
        chronometer = findViewById(R.id.chronometer_threesec)
        button = findViewById(R.id.button)
    }

    private fun setUpListeners() {
        button.setOnClickListener {
            startCountDown()
            button.isEnabled = false
        }
        chronometer.setOnChronometerTickListener {
            if (isEndOfCountDown()) {
                mediaPlayer.start()
                button.isEnabled = true
                stopCountDown()
                reset()
            }
        }
    }

}
package com.example.gym_mattas.activities

import android.os.*
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_mattas.R
import java.util.*

class AuthCodeActivity : AppCompatActivity() {

    private lateinit var codeText: TextView
    private lateinit var timerText: TextView
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_code)

        codeText = findViewById(R.id.tv_auth_code)
        timerText = findViewById(R.id.tv_timer)

        val code = generateAndShowCode()
        showOtpPopup(code)
        startCodeCycle()
    }

    private fun startCodeCycle() {
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Refreshing in: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                val newCode = generateAndShowCode()
                showOtpPopup(newCode)
                startCodeCycle()
            }
        }.start()
    }

    private fun generateAndShowCode(): String {
        val timeSlice = System.currentTimeMillis() / 30000
        val random = Random(timeSlice)
        val code = (100000 + random.nextInt(900000)).toString()
        codeText.text = code
        return code
    }

    private fun showOtpPopup(code: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("GYM_MATTAS Auth Code")
        builder.setMessage("Your login code is: $code")
        builder.setCancelable(true)

        val dialog = builder.create()
        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }, 5000)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}

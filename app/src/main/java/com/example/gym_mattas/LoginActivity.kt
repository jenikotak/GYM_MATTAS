package com.example.gym_mattas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_mattas.activities.DetectOtpActivity
import com.example.gym_mattas.R

// For otp
import android.os.*
import java.util.*
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog


class LoginActivity : AppCompatActivity() {

    private lateinit var phoneInput: EditText
    private lateinit var loginButton: Button
    private lateinit var generatedOtp: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        phoneInput = findViewById(R.id.et_phone)
        loginButton = findViewById(R.id.btn_login)

        loginButton.setOnClickListener {
            val phone = phoneInput.text.toString().trim()

            if (phone.isEmpty()) {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
            } else if (phone.length < 9) {
                Toast.makeText(this, "Enter a valid 9-digit phone number", Toast.LENGTH_SHORT).show()
            } else {
                generatedOtp = (100000 + Random(System.currentTimeMillis() / 30000).nextInt(900000)).toString()
                generateAndShowOtpPopup {
                    val intent = Intent(this, DetectOtpActivity::class.java)
                    intent.putExtra("otp_code", generatedOtp)
                    intent.putExtra("phone", phone)
                    startActivity(intent)
                }
            }
        }
    }

//    private fun generateAndShowOtpPopup(onDone: () -> Unit) {
//        val timeSlice = System.currentTimeMillis() / 30000
//        val random = Random(timeSlice)
//        val code = (100000 + random.nextInt(900000)).toString()
//
//        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//        val clip = ClipData.newPlainText("OTP Code", code)
//        clipboard.setPrimaryClip(clip)
//        Toast.makeText(this, "Code copied to clipboard", Toast.LENGTH_SHORT).show()
//
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("GYM_MATTAS Auth Code")
//        builder.setMessage("Your login code is: $code\n\n(It has been copied to clipboard)")
//        builder.setCancelable(false)
//
//        val dialog = builder.create()
//        dialog.show()
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            if (!isFinishing && dialog.isShowing) {
//                dialog.dismiss()
//                onDone() // Navigate after dialog is gone
//            }
//        }, 3000) // Shorter delay for smoother UX
//    }
// In LoginActivity after OTP is generated and verified

    //New code
    private fun generateAndShowOtpPopup(onDone: () -> Unit) {
        val timeSlice = System.currentTimeMillis() / 30000
        val random = Random(timeSlice)
        val code = generatedOtp // reuse what was already created in onClick

        // Copy to clipboard
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("OTP Code", code)
        clipboard.setPrimaryClip(clip)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("GYM_MATTAS Auth Code")
        builder.setMessage("Your login code is: $code\n\n(It has been copied to clipboard)")
        builder.setCancelable(false)

        val dialog = builder.create()
        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            if (!isFinishing && dialog.isShowing) {
                dialog.dismiss()
                onDone() // call the callback
            }
        }, 3000)
    }
}
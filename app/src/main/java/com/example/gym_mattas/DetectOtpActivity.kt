package com.example.gym_mattas.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_mattas.R
import com.example.gym_mattas.UserDetailActivity
import com.example.gym_mattas.HomeActivity
import java.util.*
import com.example.gym_mattas.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetectOtpActivity : AppCompatActivity() {

    private lateinit var otpInput: EditText
    private lateinit var verifyBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detect_otp)

        otpInput = findViewById(R.id.et_otp)
        verifyBtn = findViewById(R.id.btn_verify)

//        verifyBtn.setOnClickListener {
//            val enteredOtp = otpInput.text.toString().trim()
//            val expectedOtp = generateCurrentOtp()
//
//            if (enteredOtp == expectedOtp) {
//                Toast.makeText(this, "OTP Verified!", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, HomeActivity::class.java))
//                finish()
//            } else {
//                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
//            }
//        }

        //new
        verifyBtn.setOnClickListener {
            val enteredOtp = otpInput.text.toString().trim()
            val expectedOtp = intent.getStringExtra("otp_code")
            val phone = intent.getStringExtra("phone")

            if (enteredOtp == expectedOtp && phone != null) {
                val db = AppDatabase.getDatabase(applicationContext)

                CoroutineScope(Dispatchers.IO).launch {
                    val existingUser = db.userDao().getUserByPhone(phone)

                    withContext(Dispatchers.Main) {
                        if (existingUser != null) {
                            Toast.makeText(this@DetectOtpActivity, "Welcome back ${existingUser.name}!", Toast.LENGTH_SHORT).show()

                            // Storing session again.
                            val prefs = getSharedPreferences("gym_mattas", MODE_PRIVATE)
                            prefs.edit().putString("logged_in_user", phone).apply()

                            val intent = Intent(this@DetectOtpActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@DetectOtpActivity, "User not found, please register.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@DetectOtpActivity, UserDetailActivity::class.java)
                            intent.putExtra("phone", phone)
                            startActivity(intent)
                            finish()
                        }
                    }
                }

            } else {
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun generateCurrentOtp(): String {
        val timeSlice = System.currentTimeMillis() / 30000
        val random = Random(timeSlice)
        return (100000 + random.nextInt(900000)).toString()
    }
}

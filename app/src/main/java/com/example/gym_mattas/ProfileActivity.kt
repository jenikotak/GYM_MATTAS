package com.example.gym_mattas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_mattas.database.AppDatabase
import kotlinx.coroutines.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var bmiTextView: TextView
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        nameTextView = findViewById(R.id.nameTextView)
        phoneTextView = findViewById(R.id.phoneTextView)
        bmiTextView = findViewById(R.id.bmiTextView)
        logoutButton = findViewById(R.id.logoutButton)

        val db = AppDatabase.getDatabase(applicationContext)

        val prefs = getSharedPreferences("gym_mattas", MODE_PRIVATE)
        val phone = prefs.getString("logged_in_user", null)

        CoroutineScope(Dispatchers.IO).launch {
            val user = phone?.let { db.userDao().getUserByPhone(it) }

            withContext(Dispatchers.Main) {
                user?.let {
                    nameTextView.text = it.name
                    phoneTextView.text = "Phone: ${it.phone}"
                    bmiTextView.text = "BMI: ${it.bmi} (${it.bmiCategory})"
                }
            }
        }

        logoutButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val prefs = getSharedPreferences("gym_mattas", MODE_PRIVATE)
                prefs.edit().clear().apply()

                val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}

package com.example.gym_mattas

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup

import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.example.gym_mattas.database.AppDatabase
import kotlinx.coroutines.*


class HomeActivity : AppCompatActivity() {

    private lateinit var bmiStatusTextView: TextView
    private lateinit var exerciseListLayout: LinearLayout
    private lateinit var nameTextView: TextView
    private lateinit var greetingTextView: TextView
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize Views
        bmiStatusTextView = findViewById(R.id.bmiStatusTextView)
        exerciseListLayout = findViewById(R.id.exerciseListLayout)
        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)

        // SharedPref fetch
        val prefs = getSharedPreferences("gym_mattas", MODE_PRIVATE)
        val phone = prefs.getString("logged_in_user", null)

        val db = AppDatabase.getDatabase(applicationContext)

        CoroutineScope(Dispatchers.IO).launch {
            val user = phone?.let { db.userDao().getUserByPhone(it) }

            withContext(Dispatchers.Main) {
                if (user != null) {
                    greetingTextView.text = getGreeting()
                    nameTextView.text = user.name
                    bmiStatusTextView.text = "BMI Status: ${user.bmiCategory} (${user.bmi})"
                    showExercises(user.bmiCategory)
                } else {
                    Toast.makeText(this@HomeActivity, "User not found in DB", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Bottom Info Icon
        val infoIcon = findViewById<ImageView>(R.id.infoIcon)
        infoIcon.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }



    private fun getGreeting(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> "Good Morning 🔥"
            in 12..17 -> "Good Afternoon ☀️"
            in 18..21 -> "Good Evening 🌆"
            else -> "Good Night 🌙"
        }
    }

    private fun showExercises(category: String) {
        val exercises = when (category) {
            "Underweight" -> listOf(
                Exercise("Mass Gaining", "400 cal", "45 min"),
                Exercise("Weight Training", "350 cal", "40 min")
            )
            "Normal weight" -> listOf(
                Exercise("Full Body Fitness", "300 cal", "30 min"),
                Exercise("Regular Cardio", "280 cal", "25 min")
            )
            "Overweight", "Obese" -> listOf(
                Exercise("Fat Burning", "500 cal", "50 min"),
                Exercise("Lower Body HIIT", "450 cal", "40 min")
            )
            else -> listOf(
                Exercise("Standard Warmup", "200 cal", "20 min")
            )
        }

        exercises.forEach {
            exerciseListLayout.addView(createExerciseView(it))
        }
    }

    private fun createExerciseView(exercise: Exercise): LinearLayout {
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 24)
            }
            setBackgroundResource(R.drawable.exercise_card_bg) // ✅ Here
            elevation = 8f
        }

        val title = TextView(this).apply {
            text = exercise.title
            textSize = 20f
            setTextColor(resources.getColor(android.R.color.black))
            setPadding(0, 0, 0, 8)
        }

        val details = TextView(this).apply {
            text = "${exercise.calories} • ${exercise.duration}"
            textSize = 16f
            setTextColor(resources.getColor(android.R.color.darker_gray))
        }

        card.addView(title)
        card.addView(details)
        return card
    }

    data class Exercise(val title: String, val calories: String, val duration: String)
}
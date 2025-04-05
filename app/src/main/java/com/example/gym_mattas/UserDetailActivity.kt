///*package com.example.gym_mattas
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
//class UserDetailActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_user_detail)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}*/
//
//package com.example.gym_mattas
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Spinner
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import android.content.Intent
//import com.example.gym_mattas.activities.HomeActivity
//import java.text.SimpleDateFormat
//import java.util.*
//
//class UserDetailActivity : AppCompatActivity() {
//
//    private lateinit var nameEditText: EditText
//    private lateinit var mobileNumberEditText: EditText
//    private lateinit var ageEditText: EditText
//    private lateinit var genderSpinner: Spinner
//    private lateinit var dobEditText: EditText
//    private lateinit var membershipTypeSpinner: Spinner
//    private lateinit var membershipStartDateEditText: EditText
//    private lateinit var membershipEndDateEditText: EditText
//    private lateinit var weightEditText: EditText
//    private lateinit var heightEditText: EditText
//    private lateinit var bmiResultTextView: TextView
//    private lateinit var finishButton: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_user_detail)
//
//        // Initialize views
//        nameEditText = findViewById(R.id.nameEditText)
//        mobileNumberEditText = findViewById(R.id.mobileNumberEditText)
//        ageEditText = findViewById(R.id.ageEditText)
//        genderSpinner = findViewById(R.id.genderSpinner)
//        dobEditText = findViewById(R.id.dobEditText)
//        membershipTypeSpinner = findViewById(R.id.membershipTypeSpinner)
//        membershipStartDateEditText = findViewById(R.id.membershipStartDateEditText)
//        membershipEndDateEditText = findViewById(R.id.membershipEndDateEditText)
//        weightEditText = findViewById(R.id.weightEditText)
//        heightEditText = findViewById(R.id.heightEditText)
//        bmiResultTextView = findViewById(R.id.bmiResultTextView)
//        finishButton = findViewById(R.id.finishButton)
//
//        // Set click listener for Finish button
//       /* finishButton.setOnClickListener {
//            // Retrieve input values
//            val name = nameEditText.text.toString()
//            val mobileNumber = mobileNumberEditText.text.toString()
//            val age = ageEditText.text.toString()
//            val gender = genderSpinner.selectedItem.toString()
//            val dob = dobEditText.text.toString()
//            val membershipType = membershipTypeSpinner.selectedItem.toString()
//            val membershipStartDate = membershipStartDateEditText.text.toString()
//            val membershipEndDate = membershipEndDateEditText.text.toString()
//            val weight = weightEditText.text.toString()
//            val height = heightEditText.text.toString()
//
//            // Validate inputs
//            if (name.isNotEmpty() && mobileNumber.isNotEmpty() && age.isNotEmpty() &&
//                gender.isNotEmpty() && dob.isNotEmpty() && membershipType.isNotEmpty() &&
//                membershipStartDate.isNotEmpty() && membershipEndDate.isNotEmpty() &&
//                weight.isNotEmpty() && height.isNotEmpty()) {
//
//                // Convert weight and height to numeric values
//                val weightValue = weight.toDoubleOrNull()
//                val heightValue = height.toDoubleOrNull()
//
//                if (weightValue != null && heightValue != null) {
//                    // Calculate BMI
//                    val bmi = calculateBMI(weightValue, heightValue)
//                    // Display BMI result
//                    bmiResultTextView.text = "Your BMI is: %.2f\nYou are classified as: ${getBMICategory(bmi)}".format(bmi)
//
//                } else {
//                    Toast.makeText(this, "Please enter valid weight and height", Toast.LENGTH_SHORT).show()
//                }
//
//                // Handle any further logic such as saving the details in a database or SharedPreferences
//                Toast.makeText(this, "User details saved!", Toast.LENGTH_SHORT).show()
//
//            } else {
//                // Show error if any field is empty
//                Toast.makeText(this, "Please fill out all details", Toast.LENGTH_SHORT).show()
//            }
//        }*/
//
//        //new
//        finishButton.setOnClickListener {
//            // Retrieve input values
//            val name = nameEditText.text.toString()
//            val mobileNumber = mobileNumberEditText.text.toString()
//            val age = ageEditText.text.toString()
//            val gender = genderSpinner.selectedItem.toString()
//            val dob = dobEditText.text.toString()
//            val membershipType = membershipTypeSpinner.selectedItem.toString()
//            val membershipStartDate = membershipStartDateEditText.text.toString()
//            val membershipEndDate = membershipEndDateEditText.text.toString()
//            val weight = weightEditText.text.toString()
//            val height = heightEditText.text.toString()
//
//            if (name.isNotEmpty() && mobileNumber.isNotEmpty() && age.isNotEmpty() &&
//                gender.isNotEmpty() && dob.isNotEmpty() && membershipType.isNotEmpty() &&
//                membershipStartDate.isNotEmpty() && membershipEndDate.isNotEmpty() &&
//                weight.isNotEmpty() && height.isNotEmpty()) {
//
//                // Proceed to Home Activity
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//                finish() // Finish the current activity to prevent the user from returning here
//            } else {
//                Toast.makeText(this, "Please fill out all details", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
//
//    // Function to calculate BMI
//    private fun calculateBMI(weight: Double, height: Double): Double {
//        // BMI formula: weight (kg) / height (m)^2
//        return weight / (height * height)
//    }
//
//    // Function to determine BMI category
//    private fun getBMICategory(bmi: Double): String {
//        return when {
//            bmi < 18.5 -> "Underweight"
//            bmi in 18.5..24.9 -> "Normal weight"
//            bmi in 25.0..29.9 -> "Overweight"
//            else -> "Obesity"
//        }
//    }
//}
//
//
//this code will be removed
//this code will be removed
//


//full new code
package com.example.gym_mattas
import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.text.TextWatcher
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.gym_mattas.database.AppDatabase
import com.example.gym_mattas.database.UserEntity
import kotlinx.coroutines.*

import com.example.gym_mattas.HomeActivity

class UserDetailActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var mobileNumberEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var dobEditText: EditText
    private lateinit var membershipTypeSpinner: Spinner
    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var bmiResultTextView: TextView
    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText)
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText)
        ageEditText = findViewById(R.id.ageEditText)
        genderSpinner = findViewById(R.id.genderSpinner)
        dobEditText = findViewById(R.id.dobEditText)
        membershipTypeSpinner = findViewById(R.id.membershipTypeSpinner)
        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        bmiResultTextView = findViewById(R.id.bmiResultTextView)
        finishButton = findViewById(R.id.finishButton)

        // Set click listener for Finish button
        finishButton.setOnClickListener {
            Log.d("FLOW", "Finish button clicked")

            val name = nameEditText.text.toString()
            val phone = mobileNumberEditText.text.toString().trim()
            val weight = weightEditText.text.toString().toFloatOrNull()
            val height = heightEditText.text.toString().toFloatOrNull()

            Log.d("FLOW", "Name: $name, Phone: $phone, Weight: $weight, Height: $height")
            Log.d("FLOW", "Validation Passed: ${name.isNotEmpty() && phone.isNotEmpty() && weight != null && height != null && height > 0}")

            if (name.isNotEmpty() && phone.isNotEmpty() && weight != null && height != null && height > 0) {
                val bmi = weight / ((height / 100) * (height / 100))
                val bmiCategory = when {
                    bmi < 18.5 -> "Underweight"
                    bmi < 24.9 -> "Normal weight"
                    bmi < 29.9 -> "Overweight"
                    else -> "Obese"
                }

                val user = UserEntity(
                    name = name,
                    phone = phone,
                    bmi = bmi,
                    bmiCategory = bmiCategory
                )
                val db = AppDatabase.getDatabase(applicationContext)
                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().insertUser(user)

                    // ✅ Save session after user is fully inserted
                    val prefs = getSharedPreferences("gym_mattas", MODE_PRIVATE)
                    prefs.edit().putString("logged_in_user", phone).apply()

                    withContext(Dispatchers.Main) {
                        startActivity(Intent(this@UserDetailActivity, HomeActivity::class.java))
                        finish()
                    }
                }

            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }



        // Added the below functions for dynamic BMI calculation.
        weightEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateAndDisplayBMI()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        heightEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateAndDisplayBMI()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        // BMI formula: weight (kg) / height (m)^2
        return weight / (height * height)
    }

    private fun getBMICategory(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal weight"
            bmi in 25.0..29.9 -> "Overweight"
            else -> "Obesity"
        }
    }


    // New Function to make dynamic calculations for BMI
    private fun calculateAndDisplayBMI() {
        val weight = weightEditText.text.toString().toDoubleOrNull()
        val heightCm = heightEditText.text.toString().toDoubleOrNull()

        if (weight != null && heightCm != null && heightCm > 0) {
            val heightM = heightCm / 100
            val bmi = weight / (heightM * heightM)
            val category = when {
                bmi < 18.5 -> "Underweight"
                bmi < 24.9 -> "Normal weight"
                bmi < 29.9 -> "Overweight"
                else -> "Obese"
            }
            bmiResultTextView.text = "Your BMI is: %.2f\nCategory: %s".format(bmi, category)
        } else {
            bmiResultTextView.text = ""
        }
    }

}


package com.example.signupkotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var length8: Boolean = false
    private var uppercase: Boolean = false
    private var digit: Boolean = false
    private var special: Boolean = false
    private var success = false
    private var password: String? = null
    private var email: String? = null
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textFieldCheck()
    }

    private fun textFieldCheck() {
        password_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("TAG", "beforeTextChanged: yes")
                checkRules()
            }

        })

        username_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("TAG", "beforeTextChanged: yes")
                checkRules()
            }

        })

        email_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("TAG", "beforeTextChanged: yes")
                checkRules()
            }

        })
    }

    @SuppressLint("ResourceType")
    private fun checkRules() {
        password = password_input.text.toString()
        email = email_input.text.toString()
        username = username_input.text.toString()

        if (password!!.isNotEmpty()) {
            when {
                password!!.length < 4 -> {
                    length.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)))
                }
                password!!.length in 5..7 -> {
                    length.setCardBackgroundColor(Color.parseColor(getString(R.color.MediumLevel)))
                    length8 = false
                }
                password!!.length >= 8 -> {
                    length.setCardBackgroundColor(Color.parseColor(getString(R.color.StrongLevel)))
                    length8 = true
                }
            }
        } else {
            length.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)))
            length8 = false
        }

        uppercase = when {
            password!!.matches("(.*[A-Z].*)".toRegex()) -> {
                upperCase.setCardBackgroundColor(Color.parseColor(getString(R.color.StrongLevel)))
                true
            }
            else -> {
                upperCase.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)))
                false
            }
        }

        special = when {
            password!!.matches("(.*[:?!@#$%^&*()].*)".toRegex()) -> {
                special_chr.setCardBackgroundColor(Color.parseColor(getString(R.color.StrongLevel)))
                true
            }
            else -> {
                special_chr.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)))
                false
            }
        }

        digit = when {
            password!!.matches("(.*[0-9].*)".toRegex()) -> {
                digits.setCardBackgroundColor(Color.parseColor(getString(R.color.StrongLevel)))
                true
            }
            else -> {
                digits.setCardBackgroundColor(Color.parseColor(getString(R.color.LowLevel)))
                false
            }
        }
        checkInput()
    }

    @SuppressLint("ResourceType")
    private fun checkInput() {
        if (special && digit && length8 && uppercase && email!!.isNotEmpty() && password!!.isNotEmpty() && username!!.isNotEmpty()) {
            success = true
            signup_btn.setCardBackgroundColor(Color.parseColor(getString(R.color.enabled)))
        } else {
            success = false
            signup_btn.setCardBackgroundColor(Color.parseColor(getString(R.color.disabled)))
        }
    }

    fun signUpClicked(view: View) {
        if (success) {
            Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show()
            success = false
        } else {
            when {
                TextUtils.isEmpty(username) -> {
                    username_input.error = "Field cannot be left blank."
                    success = false
                }
                TextUtils.isEmpty(email) -> {
                    email_input.error = "Field cannot be left blank."
                    success = false
                }
                TextUtils.isEmpty(password) -> {
                    password_input.error = "Field cannot be left blank."
                    success = false
                }
            }
        }
    }
}
package com.manage1_event.event

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.manage1_event.event.R

class LoginPage : AppCompatActivity() {
    private val emailLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private val isValidLiveData = MediatorLiveData<Boolean>().apply{
        this.value = false
        addSource(emailLiveData){ email->
            val password = passwordLiveData.value
            this.value = validateForm(email, password)

        }
        addSource(passwordLiveData){ password->
            val email = emailLiveData.value
            this.value = validateForm(email, password)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val button4 = findViewById<Button>(R.id.button4)
        val emailLayout = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val passwordLayout = findViewById<EditText>(R.id.editTextTextPassword2)
        val log_in_button = findViewById<Button>(R.id.button3)

        emailLayout.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }
        passwordLayout.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(this){isValid ->
            log_in_button.isEnabled = isValid
        }
        button4.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun validateForm(email:String?, password:String?):Boolean{
        val isValidEmail = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isValidPassword = password!=null && password.isNotBlank() && password.length>=6 && password.contains(Regex("[A-Z]")) && password.contains(Regex("[0-9]")) && password.contains(Regex("[^a-zA-Z0-9 ]"))
        return isValidEmail && isValidPassword
    }
}
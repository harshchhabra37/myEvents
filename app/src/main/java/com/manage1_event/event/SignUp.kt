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
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.manage1_event.event.R


class SignUp : AppCompatActivity() {


    private val emailLiveData = MutableLiveData<String>()
    private val nameLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private val isValidLiveData = MediatorLiveData<Boolean>().apply{
        this.value = false
        addSource(emailLiveData){ email->
            val password = passwordLiveData.value
            val name = nameLiveData.value
            this.value = validateForm(email, password, name)

        }
        addSource(passwordLiveData){ password->
            val email = emailLiveData.value
            val name = nameLiveData.value
            this.value = validateForm(email, password, name)

        }
        addSource(nameLiveData){ name->
            val email = emailLiveData.value
            val password = passwordLiveData.value
            this.value = validateForm(email, password, name)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val button6 = findViewById<Button>(R.id.two)
        val emailLayout = findViewById<EditText>(R.id.three)
        val passwordLayout = findViewById<EditText>(R.id.four)
        val nameLayout = findViewById<EditText>(R.id.five)
        val sign_in_button = findViewById<Button>(R.id.six)

        emailLayout.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }
        passwordLayout.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        nameLayout.doOnTextChanged { text, _, _, _ ->
            nameLiveData.value = text?.toString()
        }
        isValidLiveData.observe(this){isValid ->
            sign_in_button.isEnabled = isValid
        }
        button6.setOnClickListener{
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

    }

    private fun validateForm(email:String?, password:String?, name:String?):Boolean{
        val isValidEmail = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isValidPassword = password!=null && password.isNotBlank() && password.length>=6 && password.contains(Regex("[A-Z]")) && password.contains(Regex("[0-9]")) && password.contains(Regex("[^a-zA-Z0-9 ]"))
        val isValidName = name!=null
        return isValidEmail && isValidPassword && isValidName
    }

}
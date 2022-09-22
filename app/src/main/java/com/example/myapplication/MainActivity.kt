package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlin.Result.Companion.success

class MainActivity : AppCompatActivity() {

    companion object {
        private val PASSWORD_REGEX = """(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[a-zA-Z0-9]{7,}\z""".toRegex()
    }

    private lateinit var btnValidar: Button
    private lateinit var btn_login: Button
    private lateinit var emailAddress: EditText
    private lateinit var passwordText: EditText
    private lateinit var errorText: TextView


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnValidar = findViewById(R.id.btnValidar)
        btn_login = findViewById(R.id.btn_login)
        emailAddress = findViewById(R.id.mail)
        passwordText = findViewById(R.id.contraseña)
        errorText = findViewById(R.id.message)

        clearAllErrorMessages()
        setListeners()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setListeners() {
        btnValidar.setOnClickListener {
            if (validarMail()) {
                if (validarPassword()) {
                    showSuccess("email y contraseña válidos")

                }
            }
        }

        addTextClearListener(emailAddress)
        addTextClearListener(passwordText)
    }

    private fun clearAllErrorMessages() {
        errorText.visibility = View.INVISIBLE
    }

    private fun addTextClearListener(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                clearAllErrorMessages()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun validarMail(): Boolean {
        val mail = emailAddress.text

        if (mail.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            showError("email inválido")
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun validarPassword(): Boolean {
        val password = passwordText.text

        if (password.isBlank() || !password.matches(PASSWORD_REGEX)) {
            showError("contraseña inválida")
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showError(message: CharSequence) {
        errorText.text = message
        errorText.setTextColor(getColor(R.color.error))
        errorText.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showSuccess(message: CharSequence) {
        errorText.text = message
        errorText.setTextColor(getColor(R.color.success))
        errorText.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun ingresar() {
        if (validarMail() && validarPassword()) {
            btn_login.setOnClickListener() {
                setContentView(R.layout.bienvenido)

            }
        }
    }
}



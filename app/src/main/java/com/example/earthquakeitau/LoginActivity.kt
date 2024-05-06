package com.example.earthquakeitau

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            startActivityForResult(RegisterActivity.newIntent(this), 1)
        }

        loginButton.setOnClickListener {
            // Redirigir al usuario a la pantalla EarthquakeScreen, sin validación con Firebase
            val intent = Intent(this, EarthquakeScreen::class.java)
            startActivityForResult(intent, 2)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // La actividad RegisterActivity ha finalizado correctamente
            // Realiza alguna acción adicional si es necesario
        } else if (requestCode == 2) {
            // La actividad EarthquakeScreen ha finalizado
            // Realiza alguna acción adicional si es necesario
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }

        fun newIntent(context: Context, resultCode: Int, data: Intent): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtras(data)
            intent.putExtra("resultCode", resultCode)
            return intent
        }
    }
}
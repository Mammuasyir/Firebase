package com.humam.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.humam.firebase.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val password = binding.edtPasswordLogin.text.toString()
            if (email.isEmpty()) {
                binding.edtEmailLogin.error = "Email harus di isi!"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmailLogin.error = "Email harus dengan format yang benar!"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPasswordLogin.error = "Password harus di isi!"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.edtPasswordLogin.error = "Password Minimal 6 karakter!"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            loginUserToFirebase(email, password)
        }

        binding.tvRegister.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
    }

    private fun loginUserToFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val i = Intent(this, NavigationActivity::class.java)
                    Toast.makeText(this, "$email berhasil login", Toast.LENGTH_SHORT).show()
                    startActivity(i)
                    finish()
                } else{
                    Toast.makeText(this, "$email gagal login", Toast.LENGTH_SHORT).show()
                }
            }

    }
}
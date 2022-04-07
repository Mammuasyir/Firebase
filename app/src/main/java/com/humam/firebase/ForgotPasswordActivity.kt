package com.humam.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.humam.firebase.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnResetPassword.setOnClickListener {
            val email = binding.edtInputResetEmail.text.toString()
            val edtEmail = binding.edtInputResetEmail
            if (email.isEmpty()){
                edtEmail.error = "Email diperlukan!"
                edtEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edtEmail.error = "Email tidak valid"
                edtEmail.requestFocus()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    val i = Intent(this, LoginActivity::class.java)
                    Toast.makeText(applicationContext, "Email verifikasi telah dikirim", Toast.LENGTH_SHORT).show()
                    startActivity(i)
                    finish()
                }
                else {
                    edtEmail.error = "${it.exception?.message}"
                    edtEmail.requestFocus()
                }
            }
        }
    }
}
package com.humam.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.humam.firebase.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmailRegist.text.toString()
            val password = binding.edtPasswordRegist.text.toString()


            if (email.isEmpty()) {
                binding.edtEmailRegist.error = "Email harus di isi!"
                binding.edtEmailRegist.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmailRegist.error = "Email harus dengan format yang benar!"
                binding.edtEmailRegist.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPasswordRegist.error = "Password harus di isi!"
                binding.edtPasswordRegist.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.edtPasswordRegist.error = "Password Minimal 6 karakter!"
                binding.edtPasswordRegist.requestFocus()
                return@setOnClickListener
            }

            daftarUserFirebase(email, password)


        }
        binding.tvLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        }

    private fun daftarUserFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
//                    val i = Intent(this, MainActivity::class.java)
//                    startActivity(i)
//                    finish()
                Intent(this, NavigationActivity::class.java).also { intent ->
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                } else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }
}
package com.humam.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.humam.firebase.databinding.ActivityUpdateEmailBinding
import com.humam.firebase.databinding.ActivityUpdatePasswordBinding

class UpdateEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateEmailBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        binding.cardVerifyPassword.visibility = View.VISIBLE
        binding.cardUpdateEmail.visibility = View.GONE
        binding.btnOtorisasiPassword.setOnClickListener {
            val pass = binding.edtPassword.text.toString()
            if (pass.isEmpty()){
                binding.edtPassword.error = "Password tidak boleh kosong"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            //Buat credential user
            user.let {
                val userKredensial = EmailAuthProvider.getCredential(it?.email!!, pass)
                it.reauthenticate(userKredensial).addOnCompleteListener { Task ->
                    when{
                        Task.isSuccessful -> {
                            binding.cardVerifyPassword.visibility = View.GONE
                            binding.cardUpdateEmail.visibility = View.VISIBLE
                        }
                        Task.exception is FirebaseAuthInvalidCredentialsException -> {
                            binding.edtPassword.error = "Password Salah"
                            binding.edtPassword.requestFocus()
                        }else -> {
                        val toast = Toast.makeText(this, "${Task.exception?.message}", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                    }
                    }
                }
            }
            binding.btnUpdateEmail.setOnClickListener updateEmail@{

                val email = binding.edtEmail.text.toString()

                if (email.isEmpty()){
                    binding.edtEmail.error = "Password baru dibutuhkan!"
                    binding.edtEmail.requestFocus()
                    return@updateEmail
                }

                if (email.length < 6){
                    binding.edtEmail.error = "Password minimal 6 karakter!"
                    binding.edtEmail.requestFocus()
                    return@updateEmail
                }


                user?.let {
                    user.updateEmail(email).addOnCompleteListener {
                        if (it.isSuccessful){

                            val toast = Toast.makeText(this, "Email Updated", Toast.LENGTH_SHORT)
                            toast.show()
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }

    }
}
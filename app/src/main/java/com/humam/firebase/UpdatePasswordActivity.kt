package com.humam.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.humam.firebase.databinding.ActivityUpdatePasswordBinding

class UpdatePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdatePasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        binding.cardVerifyPassword.visibility = View.VISIBLE
        binding.cardUpdatePassword.visibility = View.GONE
        binding.btnOtorisasiPassword.setOnClickListener {
            val pass = binding.edtNowPassword.text.toString()
            if (pass.isEmpty()){
                binding.edtNowPassword.error = "Password tidak boleh kosong"
                binding.edtNowPassword.requestFocus()
                return@setOnClickListener
            }

            //Buat credential user
            user.let {
                val userKredensial = EmailAuthProvider.getCredential(it?.email!!, pass)
                it.reauthenticate(userKredensial).addOnCompleteListener { Task ->
                    when{
                        Task.isSuccessful -> {
                            binding.cardVerifyPassword.visibility = View.GONE
                            binding.cardUpdatePassword.visibility = View.VISIBLE
                        }
                        Task.exception is FirebaseAuthInvalidCredentialsException -> {
                            binding.edtNowPassword.error = "Password Salah"
                            binding.edtNowPassword.requestFocus()
                        }else -> {
                            val toast = Toast.makeText(this, "${Task.exception?.message}", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0, 0)
                        toast.show()
                        }
                    }
                }
            }
            binding.btnUpdatePassword.setOnClickListener updatePassword@{

                val passBaru = binding.edtNewPassword.text.toString()
                val passKonfirmasi = binding.edtNewPasswordConfirm.text.toString()

                if (passBaru.isEmpty()){
                    binding.edtNewPassword.error = "Password baru dibutuhkan!"
                    binding.edtNewPassword.requestFocus()
                    return@updatePassword
                }

                if (passBaru.length < 6){
                    binding.edtNewPassword.error = "Password minimal 6 karakter!"
                    binding.edtNewPassword.requestFocus()
                    return@updatePassword
                }


                if (passBaru != passKonfirmasi){
                    binding.edtNewPasswordConfirm.error = "Password tidak sama!"
                    binding.edtNewPasswordConfirm.requestFocus()
                    return@updatePassword
                }

                user?.let {
                    user.updatePassword(passBaru).addOnCompleteListener {
                        if (it.isSuccessful){

                            val toast = Toast.makeText(this, "Password berhasil diubah, silahkan login kembali!", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP, 0, 0)
                            toast.show()
                            logoutAccount()

                        }else{
                            Toast.makeText(applicationContext, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }


    }

    private fun logoutAccount() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
}
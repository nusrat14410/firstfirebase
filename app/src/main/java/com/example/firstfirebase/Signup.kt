package com.example.firstfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstfirebase.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.alreadyHaveAcLoginTV.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.signUpBT.setOnClickListener {
            signUpUser()
        }

        }

    private fun signUpUser() {
        val email=binding.signUpEmailTIET.text.toString().trim()
        val createPassword=binding.signUpCrePasswordTIET.text.toString().trim()
        val confrimPassword=binding.signUpConPasswordTIET.text.toString().trim()

        if(email.isEmpty() || createPassword.isEmpty() || confrimPassword.isEmpty()){
            Toast.makeText(this, "Pelease fill all flieds", Toast.LENGTH_SHORT).show()
            return
        }
        if (confrimPassword!=createPassword){
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.createUserWithEmailAndPassword(email,confrimPassword).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Sign Up successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Home::class.java))
            }
            else{
                Toast.makeText(this, "Sign Up failed", Toast.LENGTH_SHORT).show()
            }



        }
}
}
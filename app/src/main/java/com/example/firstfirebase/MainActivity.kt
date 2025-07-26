package com.example.firstfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth=FirebaseAuth.getInstance()

        binding.createAcSignUpTV.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
        }

        binding.loginBT.setOnClickListener {
            loginUser()

        }

    }

    private fun loginUser() {
        val email=binding.loginEmailTIET.text.toString().trim()
        val password=binding.loginPasswordTIET.text.toString().trim()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity((Intent(this,Home::class.java)))
            }
            else{
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
        
    }
    
    
}
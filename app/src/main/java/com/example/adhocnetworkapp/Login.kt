package com.example.adhocnetworkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var btn_login:Button
    private lateinit var btn_signup:Button
    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btn_login = findViewById(R.id.btn_login)
        btn_signup = findViewById(R.id.btn_signup)


        btn_signup.setOnClickListener(){
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener(){
            val email = email.text.toString()
            val password = password.text.toString()

            login(email,password)
        }

    }

    private fun login(email:String , password:String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // LogIn to the user account
                    val intent = Intent(this@Login,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login,"Something wrong happened! Make sure that you passed the correct email and password",Toast.LENGTH_SHORT).show()
                }
            }
    }
}
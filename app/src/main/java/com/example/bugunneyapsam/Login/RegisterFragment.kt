package com.example.bugunneyapsam.Login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController

import com.example.bugunneyapsam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var  ad : String
    private lateinit var emaill :String
    private lateinit var sifre : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   auth = FirebaseAuth.getInstance()
        emaill = email.text.toString()
        ad = userName.text.toString()
        sifre = password.text.toString()

        button2.setOnClickListener {
          signup()

        }
        btnLogin.setOnClickListener {
            val action= RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
           it.findNavController().navigate(action)
        }
    }

    private  fun signup() {
        ad = userName.text.toString().trim()
         emaill = email.text.toString().trim()
         sifre = password.text.toString().trim()
        if (TextUtils.isEmpty(ad) ) {
            Toast.makeText(context, "Boş", Toast.LENGTH_LONG).show()
        }
       else if (TextUtils.isEmpty(emaill) ) {
            Toast.makeText(context, "Boş", Toast.LENGTH_LONG).show()
        }
       else if (TextUtils.isEmpty(sifre) ) {
            Toast.makeText(context, "Boş", Toast.LENGTH_LONG).show()
        }
      else  if(sifre.length<6){
            Toast.makeText(context, "Şifre 6 haneli olmalı", Toast.LENGTH_LONG).show()
        }
        else {
            auth.createUserWithEmailAndPassword(emaill, sifre)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        var update = UserProfileChangeRequest.Builder().setDisplayName(ad).build()
                        auth.currentUser!!.updateProfile(update)
                        Toast.makeText(context, "BAŞARILI KAYIT", Toast.LENGTH_LONG).show()

                        auth.signOut()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

        }

    }
}
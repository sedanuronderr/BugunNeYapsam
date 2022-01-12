package com.example.bugunneyapsam.Login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.bugunneyapsam.R

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*

// TODO: Rename parameter arguments, choose names that match

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private lateinit var email : String
    private lateinit var password : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        email = emaill.text.toString()
        password = passwordd.text.toString()
        if(auth.currentUser != null){

            Navigation.findNavController(view).navigate(R.id.kategoriFragment)

        }


        button.setOnClickListener {
            login()
        }
        btnReg.setOnClickListener {
            val action= LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            it.findNavController().navigate(action)
        }
    }

    private fun login(){
         email =emaill.text.toString().trim()
         password= passwordd.text.toString().trim()
        if (TextUtils.isEmpty(email) ) {
            Toast.makeText(context, "Email Boş", Toast.LENGTH_LONG).show()
        }
       else if (TextUtils.isEmpty(password) ) {
            Toast.makeText(context, "Şifre Boş", Toast.LENGTH_LONG).show()
        }
        else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(context,"Welcome : "+auth.currentUser!!.displayName, Toast.LENGTH_LONG).show()
                        view?.let { Navigation.findNavController(it).navigate(R.id.kategoriFragment) }

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}
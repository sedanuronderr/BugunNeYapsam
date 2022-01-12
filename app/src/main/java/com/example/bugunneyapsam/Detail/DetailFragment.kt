package com.example.bugunneyapsam.Detail

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.example.bugunneyapsam.Listeİslemleri.ListeFragmentArgs
import com.example.bugunneyapsam.R
import com.example.bugunneyapsam.databinding.FragmentDetailBinding
import com.example.bugunneyapsam.databinding.FragmentListeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import android.text.method.ScrollingMovementMethod
import com.example.bugunneyapsam.Class.favori
import kotlinx.android.synthetic.main.fragment_liste.*


class DetailFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var   database: DatabaseReference
    val bundle : DetailFragmentArgs by navArgs()
    private lateinit var fragmentbinding: FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= FragmentDetailBinding.bind(view)
        fragmentbinding = binding

        auth = Firebase.auth
        val db = Firebase.database
        database = db.getReference("users")

       val scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
        scaleAnimation?.setDuration(500)
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation?.setInterpolator(bounceInterpolator)





        val yemekismi = bundle.tarifnesnesi.yemekismi
        val tarif = bundle.tarifnesnesi.tarif
        val resim = bundle.tarifnesnesi.yemekresmi
        binding.textView.text = yemekismi
      //  binding.textView4.text= Editable.Factory.getInstance().newEditable(tarif)
        binding.textView4.setMovementMethod(ScrollingMovementMethod())
        binding.textView4.text = tarif
        binding.imageView3.load(resim){
            crossfade(true)
            crossfade(1000)

        }

        
        binding.toggleButton.setOnCheckedChangeListener(object :View.OnClickListener,CompoundButton.OnCheckedChangeListener{
            override fun onClick(v: View) {


            }

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                buttonView?.startAnimation(scaleAnimation)

                if (isChecked) {
                val favori = favori("",resim.toString(),yemekismi.toString())
                    database.child(auth.currentUser!!.uid).push().setValue(favori)

                } else {
                    Toast.makeText(requireContext(),"Favorilerden Çıkarıldı",Toast.LENGTH_SHORT).show()
                }

            }


        })

    }

  //  fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}
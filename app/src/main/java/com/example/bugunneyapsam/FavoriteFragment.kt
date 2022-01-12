package com.example.bugunneyapsam

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bugunneyapsam.Class.Kategori
import com.example.bugunneyapsam.Class.favori
import com.example.bugunneyapsam.Class.tarifler
import com.example.bugunneyapsam.KategoriAdapter.ListeAdapter
import com.example.bugunneyapsam.KategoriAdapter.favoriAdapter
import com.example.bugunneyapsam.databinding.FragmentFavoriteBinding
import com.example.bugunneyapsam.databinding.FragmentListeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class FavoriteFragment : Fragment() {
    private lateinit var fragmentbinding: FragmentFavoriteBinding
    private lateinit var adapter: favoriAdapter
    private lateinit var favorilist: ArrayList<favori>

    private lateinit var auth: FirebaseAuth
    lateinit var   database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= FragmentFavoriteBinding.bind(view)
        fragmentbinding=binding


        auth = Firebase.auth
        val db = Firebase.database
        database = db.getReference("users")


        binding.recyclerfavori.layoutManager =LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
         favorilist= ArrayList<favori>()
        adapter = favoriAdapter(requireContext(),favorilist)
        binding.recyclerfavori.adapter = adapter
        binding.recyclerfavori.setHasFixedSize(true)
tumKategoriler()
    }


    fun tumKategoriler(){
        val sorgu = database.child(auth.currentUser!!.uid)
        sorgu.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
              favorilist.clear()

                for(c in snapshot.children) {
                    val post =c.getValue(favori::class.java)
                    if (post != null) {
                        post.favoriid = c.key
                            favorilist.add(post)
                    }
                    adapter.notifyDataSetChanged()
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}
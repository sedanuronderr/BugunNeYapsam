package com.example.bugunneyapsam.Kategoriİslemler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bugunneyapsam.Class.Kategori

import com.example.bugunneyapsam.KategoriAdapter.KategoriAdapter
import com.example.bugunneyapsam.R
import com.example.bugunneyapsam.databinding.FragmentKategoriBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class KategoriFragment : Fragment() {

    private lateinit var fragmentbinding: FragmentKategoriBinding
    private lateinit var cards: ArrayList<Kategori>
    private lateinit var adapter: KategoriAdapter
    lateinit var   database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= FragmentKategoriBinding.bind(view)
        fragmentbinding=binding
        val db = Firebase.database
        database = db.getReference("Kategoriler")





     /*   val u1 = Kategori("","Corba", "corba")
        val u2 = Kategori("","Salata", "salata")
        val u3 = Kategori("","Et", "et")
        val u4 = Kategori("","Tavuk", "tavuk")
        val u5 = Kategori("","Sebze", "sebze")
        val u6 = Kategori("","Pilav", "pilav")

        cards.add(u1)
        cards.add(u2)
        cards.add(u3)
        cards.add(u4)
        cards.add(u5)
        cards.add(u6)
        cards = ArrayList<Kategori>()
        database
            .setValue(cards)*/
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2,LinearLayoutManager.VERTICAL,false)
        cards = ArrayList<Kategori>()
        adapter= KategoriAdapter(requireContext(),cards)
        binding.recyclerView.adapter=adapter
tumKategoriler()

    }
    fun tumKategoriler(){
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cards.clear()
                for(c in snapshot.children){
                    val kategori = c.getValue(Kategori::class.java)

                    if(kategori != null){
                        kategori.kategoriid =c.key
                        cards.add(kategori)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}
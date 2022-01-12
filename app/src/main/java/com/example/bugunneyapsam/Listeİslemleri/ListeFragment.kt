package com.example.bugunneyapsam.Listeİslemleri

import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bugunneyapsam.Class.Kategori
import com.example.bugunneyapsam.Class.tarifler

import com.example.bugunneyapsam.KategoriAdapter.ListeAdapter
import com.example.bugunneyapsam.R

import com.example.bugunneyapsam.databinding.FragmentListeBinding

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class ListeFragment : Fragment() {
    private lateinit var fragmentbinding: FragmentListeBinding
    private lateinit var adapter: ListeAdapter
    private lateinit var tariflist: ArrayList<tarifler>

   val bundle : ListeFragmentArgs by navArgs()
    lateinit var   database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentListeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= FragmentListeBinding.bind(view)
        fragmentbinding=binding

        binding.toolbar.inflateMenu(R.menu.menu)
        val gelenisim = bundle.kategorinesnesi.kategoriad
        val gelenresim = bundle.kategorinesnesi.kategoriresim
        val gelenkategoriId = bundle.kategorinesnesi.kategoriid

        val db = Firebase.database
        database = db.getReference("Tarifler")


     binding.toolbar.title= "Menuler"
    binding.toolbar.setOnMenuItemClickListener {
        when (it.itemId){
            R.id.action -> {
                Navigation.findNavController(view).navigate(R.id.favoriteFragment)
                // Navigate to settings screen
                true
            }
            else -> false

        }
    }

        binding.recyclerView1.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
   tariflist = ArrayList<tarifler>()
        adapter = ListeAdapter(tariflist)
        binding.recyclerView1.adapter =adapter
        binding.recyclerView1.setHasFixedSize(true)

        tarifbyKategoriler(gelenisim)
    }
    fun tarifbyKategoriler(kategori_ad:String?){
        val sorgu = database.orderByChild("kategoriad").equalTo(kategori_ad)

        sorgu.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tariflist.clear()
                for(c in snapshot.children){
                    val kategori = c.getValue(tarifler::class.java)

                    if(kategori != null){
                        kategori.yemekid =c.key
                        tariflist.add(kategori)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)


        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action -> {
                // navigate to settings screen

          return   true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}
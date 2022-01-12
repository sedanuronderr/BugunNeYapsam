package com.example.bugunneyapsam.KategoriAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.bugunneyapsam.Class.Kategori
import com.example.bugunneyapsam.Class.tarifler
import com.example.bugunneyapsam.Kategoriİslemler.KategoriFragmentDirections
import com.example.bugunneyapsam.Listeİslemleri.ListeFragmentDirections
import com.example.bugunneyapsam.R
import com.example.bugunneyapsam.databinding.KategoriCardBinding
import com.example.bugunneyapsam.databinding.ListeCardBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListeAdapter(var tarif :ArrayList<tarifler>)  : RecyclerView.Adapter<ListeAdapter.tariff>()  {


    class tariff (val binding : ListeCardBinding): RecyclerView.ViewHolder(binding.root){

    }
   /* private val diffCallback = object : DiffUtil.ItemCallback<tarifler>() {
        override fun areItemsTheSame(oldItem: tarifler, newItem: tarifler): Boolean {
            return oldItem.yemekid == newItem.yemekid
        }

        override fun areContentsTheSame(oldItem: tarifler, newItem: tarifler): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var ttarifler: List<tarifler>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tariff {


        return tariff(ListeCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: tariff, position: Int) {
        val kategorilist = tarif[position]



        holder.binding.apply {
            imageView.load(kategorilist.yemekresmi) {
                crossfade(true)
                crossfade(1000)
                transformations(CircleCropTransformation())
            }
            textView5.text =kategorilist.yemekismi
            card.setOnClickListener {
                val gecis = ListeFragmentDirections.actionListeFragmentToDetailFragment(kategorilist)
                Navigation.findNavController(it).navigate(gecis)
            }
        }

    }

    override fun getItemCount(): Int {
        return tarif.size
    }


}

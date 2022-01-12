package com.example.bugunneyapsam.KategoriAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.bugunneyapsam.Class.Kategori
import com.example.bugunneyapsam.Kategoriİslemler.KategoriFragmentDirections
import com.example.bugunneyapsam.MainActivity
import com.example.bugunneyapsam.R
import com.example.bugunneyapsam.databinding.KategoriCardBinding

class KategoriAdapter (val mContext: Context, val kategori1 :ArrayList<Kategori>) :
    RecyclerView.Adapter<KategoriAdapter.kategori>()  {


    class kategori (val binding : KategoriCardBinding): RecyclerView.ViewHolder(binding.root){




    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kategori {


        return kategori(KategoriCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: kategori, position: Int) {
        val kategorilist = kategori1.get(position)

        holder.binding.apply {
            imageview.setImageResource(mContext.resources.getIdentifier(kategorilist.kategoriresim,"drawable",mContext.packageName))


            card1.setOnClickListener {
            val gecis = KategoriFragmentDirections.actionKategoriFragmentToListeFragment(kategorilist)
                Navigation.findNavController(it).navigate(gecis)
            }
        }

    }

    override fun getItemCount(): Int {
        return kategori1.size
    }


}

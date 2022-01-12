package com.example.bugunneyapsam.KategoriAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.bugunneyapsam.Class.Kategori
import com.example.bugunneyapsam.Class.favori
import com.example.bugunneyapsam.Kategoriİslemler.KategoriFragmentDirections
import com.example.bugunneyapsam.databinding.FavoriCardBinding
import com.example.bugunneyapsam.databinding.KategoriCardBinding

class favoriAdapter (val mContext: Context ,val favori:ArrayList<favori>) :
    RecyclerView.Adapter<favoriAdapter.favoriview>()  {


    class favoriview (val binding: FavoriCardBinding): RecyclerView.ViewHolder(binding.root){




    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favoriview {


        return favoriview(FavoriCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: favoriview, position: Int) {

        val kategorilist = favori.get(position)
   holder.binding.apply {
       favoriisim.text= kategorilist.favoriisim
       favoriimageView.load(kategorilist.favoriresim){
           crossfade(true)
           crossfade(1000)
           transformations(CircleCropTransformation())
       }
   }

    }

    override fun getItemCount(): Int {
        return  favori.size
    }


}
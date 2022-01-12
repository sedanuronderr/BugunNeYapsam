package com.example.bugunneyapsam.Class



import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable
@IgnoreExtraProperties
data class tarifler(var yemekid:String? = null,var yemekismi:String? = null,var yemekresmi:String? = null,var tarif:String? = null,var kategoriad:String? = null):Serializable {
}

package com.example.bugunneyapsam.Class

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable
@IgnoreExtraProperties
data class favori(var favoriid:String?=null, val favoriresim:String?=null, val favoriisim:String?=null) : Serializable {
}
package com.br.beerlist.beerlist.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Beer : RealmObject(), Serializable {

    @PrimaryKey
    private var id:Int = 0

    @SerializedName("image_url")
    var imageUrl:String = ""

    var name:String = ""

    var description:String = ""

    var tagline:String = ""

    var isFavorite:Boolean = false

}
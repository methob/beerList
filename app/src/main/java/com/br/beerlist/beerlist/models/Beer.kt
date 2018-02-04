package com.br.beerlist.beerlist.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Beer() : RealmObject(), Parcelable {

    @PrimaryKey // Chave Primaria
    var id:Int = 0

    @SerializedName("image_url")
    var imageUrl:String = ""

    var name:String = ""

    var description:String = ""

    var tagline:String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        imageUrl = parcel.readString()
        name = parcel.readString()
        description = parcel.readString()
        tagline = parcel.readString()
    } // serializa Objeto

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(imageUrl)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(tagline)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Beer> {
        override fun createFromParcel(parcel: Parcel): Beer {
            return Beer(parcel)
        }

        override fun newArray(size: Int): Array<Beer?> {
            return arrayOfNulls(size)
        }
    }

}
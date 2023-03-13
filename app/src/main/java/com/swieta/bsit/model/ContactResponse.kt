package com.swieta.bsit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactResponse(
    @SerializedName("name")
    var name: String? ,
    @SerializedName("no_telp")
    var noTelp: String?

):Parcelable
package com.swieta.bsit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileResponse(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("joined_date")
    var joinedDate: String? = null,
    @SerializedName("image_url")
    var imageUrl: String? = null,
    @SerializedName("no_telp")
    var noTelp: String? = null,
    @SerializedName("status")
    var status: Int? = null,
    @SerializedName("lat")
    var lat: Double? = null,
    @SerializedName("lng")
    var lng: Double? = null
):Parcelable
package com.swieta.bsit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("image_url")
    var imageUrl: String? ,
    @SerializedName("metode_trf")
    var metodeTrf: String? ,
    @SerializedName("name")
    var name: String? ,
    @SerializedName("nominal_saldo")
    var nominalSaldo: String? ,
    @SerializedName("flag_debit_credit")
    var flagDebitCredit: Int?
):Parcelable
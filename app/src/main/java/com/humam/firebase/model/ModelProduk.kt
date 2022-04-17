package com.humam.firebase.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelProduk (

    var namaProduk: String? = null,
    var hargaProduk: String? = null,
    var gambarProduk: Int = 0

) : Parcelable
package com.ralya.dictimath.home.dashboard.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KelasSatu (

    var namakelas:String ?= "",
    var kelas:String ?= "",
    var hargakelas:String ?= "",
    var bannerkelas:String ?= "",
    var waktukelas:String ?= ""

) : Parcelable
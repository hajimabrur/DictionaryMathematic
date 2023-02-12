package com.ralya.dictimath.home.exercise.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Latihan (

    var namakelas:String ?= "",
    var kelas:String ?= "",
    var skor:String ?= "",


) : Parcelable


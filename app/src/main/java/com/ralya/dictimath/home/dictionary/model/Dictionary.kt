package com.ralya.dictimath.home.dictionary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dictionary (

    var Istilah:String ?= "",
    var makna:String ?= ""

) : Parcelable
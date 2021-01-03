package com.example.firebaseapp

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class WeightData(
    var weight: Float = 0f,
    var time: Long? = 0
) {

}


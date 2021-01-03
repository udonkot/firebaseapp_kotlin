package com.example.firebaseapp

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Weight(
    var weight: Int? = 0
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf("weight" to  weight)
    }
 }
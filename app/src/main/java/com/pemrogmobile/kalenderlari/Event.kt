package com.pemrogmobile.kalenderlari

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val name: String,
    val description: String,
    val date: String,
    val link: String,
    val photo: Int
): Parcelable
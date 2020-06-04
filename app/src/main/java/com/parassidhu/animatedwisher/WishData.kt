package com.parassidhu.animatedwisher

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WishData(
    val firstGreeting: String,
    val lastGreeting: String,
    val firstName: String,
    val lastName: String
): Parcelable
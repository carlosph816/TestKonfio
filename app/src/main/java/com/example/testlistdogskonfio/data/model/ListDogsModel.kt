package com.example.testlistdogskonfio.data.model

import android.os.Parcelable
import com.example.testlistdogskonfio.data.model.response.Dog
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class ListDogsModel (
    val listDogs:@RawValue List<Dog> = listOf()
) : Parcelable

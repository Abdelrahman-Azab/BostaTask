package com.azab.bostaTask.repository

import com.azab.bostaTask.model.Photo
import com.azab.bostaTask.network.ApiInterface
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val api:ApiInterface){
    suspend fun getPhotos(albumId:Int) : List<Photo>
    {
        return api.getPhotos(albumId)
    }
}
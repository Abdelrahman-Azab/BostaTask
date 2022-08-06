package com.azab.bostaTask.network

import com.azab.bostaTask.model.Album
import com.azab.bostaTask.model.Photo
import com.azab.bostaTask.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("users")
    suspend fun getUsers():List<User>

    @GET("albums")
    suspend fun getAlbums(@Query("userId") userId:Int):List<Album>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId:Int):List<Photo>


}
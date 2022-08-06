package com.azab.bostaTask.repository

import com.azab.bostaTask.model.Album
import com.azab.bostaTask.model.User
import com.azab.bostaTask.network.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository
@Inject constructor(private val api:ApiInterface) {
    suspend fun getUsers():List<User>
    {
        return api.getUsers();
    }
    suspend fun getAlbums(userId:Int):List<Album>
    {
        return api.getAlbums(userId);
    }
}
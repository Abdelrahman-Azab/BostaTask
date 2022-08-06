package com.azab.bostaTask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azab.bostaTask.model.Album
import com.azab.bostaTask.model.User
import com.azab.bostaTask.repository.ProfileRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository): ViewModel()  {
    //For Users
    private var usersResponse :MutableLiveData<List<User>> = MutableLiveData()
     val usersResponseU :MutableLiveData<List<User>> get() = usersResponse

    //For Albums
    private var albumResponse :MutableLiveData<List<Album>> = MutableLiveData()
    val albumResponseA :MutableLiveData<List<Album>> get() = albumResponse



    fun getUsers()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val response = profileRepository.getUsers()
            usersResponse.postValue(response)
        }
    }
    fun getAlbums(userId:Int)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val response = profileRepository.getAlbums(userId)
            albumResponse.postValue(response)
        }
    }
}
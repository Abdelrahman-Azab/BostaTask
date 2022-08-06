package com.azab.bostaTask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azab.bostaTask.model.Album
import com.azab.bostaTask.model.Photo
import com.azab.bostaTask.model.User
import com.azab.bostaTask.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val photoRepository: PhotoRepository):ViewModel() {
    //For Users
    private var photosResponse : MutableLiveData<List<Photo>> = MutableLiveData()
    val photosResponseP : MutableLiveData<List<Photo>> get() = photosResponse

    fun getPhotos(albumId:Int)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val response = photoRepository.getPhotos(albumId)
            photosResponse.postValue(response)
        }
    }

}
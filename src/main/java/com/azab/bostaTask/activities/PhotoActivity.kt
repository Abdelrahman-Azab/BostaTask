package com.azab.bostaTask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azab.bostaTask.R
import com.azab.bostaTask.adapter.PhotoAdapter
import com.azab.bostaTask.databinding.ActivityPhotoBinding
import com.azab.bostaTask.model.Photo
import com.azab.bostaTask.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoActivity : AppCompatActivity() {
    private lateinit var photoBinding:ActivityPhotoBinding
    private lateinit var photoAdapter: PhotoAdapter
    private val viewModel:PhotoViewModel by viewModels()
    private val photosList = ArrayList<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoBinding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(photoBinding.root)
        recyclerViewProperties()
        dataObserve()
        searchAboutphoto()

    }


    private fun dataObserve()
    {
        viewModel.getPhotos(intent.getIntExtra("id",1))
        viewModel.photosResponseP.observe(this)
        {
            photosList.addAll(it)
            addDataToRecyclerView(it)
        }
    }
    private fun searchAboutphoto()
    {
        photoBinding.searchEt.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
               val searchText= p0.toString()
                addDataAfterSearch(searchText)
            }
        })
    }

    private fun addDataAfterSearch(searchText: String) {
        val photoListAfterSearch =ArrayList<Photo>()
        for(item in photosList)
        {
            if(searchText == item.title)
            {
                photoListAfterSearch.add(item)
            }
        }
        addDataToRecyclerView(photoListAfterSearch)

    }

    private fun recyclerViewProperties()
    {
        photoAdapter = PhotoAdapter()
        photoBinding.photosRv.layoutManager = GridLayoutManager(this,3)
        photoBinding.photosRv.adapter = photoAdapter

    }
    private fun addDataToRecyclerView(photosList: List<Photo>)
    {
        photoAdapter.addNewPhotos(photosList)
    }
}
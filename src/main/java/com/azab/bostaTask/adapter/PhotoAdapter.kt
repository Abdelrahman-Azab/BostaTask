package com.azab.bostaTask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azab.bostaTask.R
import com.azab.bostaTask.databinding.ItemPhotoBinding
import com.azab.bostaTask.model.Photo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions

class PhotoAdapter:RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> (){
private val photosList = ArrayList<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val photoBinding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PhotoViewHolder(photoBinding)

    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.photobind(photosList[position])
    }

    override fun getItemCount(): Int {
        return photosList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addNewPhotos(newPhotos: List<Photo>) {
        photosList.clear()
        photosList.addAll(newPhotos)
        notifyDataSetChanged()
    }


    class PhotoViewHolder(private val photoBinding: ItemPhotoBinding):RecyclerView.ViewHolder(photoBinding.root) {
        fun photobind(item:Photo)
        {
            val options= RequestOptions().placeholder(R.drawable.loadimg).error(R.drawable.loadimg)

            val context = photoBinding.root.context
           Glide.with(context).load(item.url).dontAnimate().apply(options).into(photoBinding.photoIv)

        }


    }
}
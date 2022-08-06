package com.azab.bostaTask.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azab.bostaTask.activities.PhotoActivity
import com.azab.bostaTask.databinding.ItemAlbumBinding
import com.azab.bostaTask.model.Album

class AlbumAdapter(private val albumsList : List<Album>) :RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
       val albumBinding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlbumViewHolder(albumBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.albumbind(albumsList[position])
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    class AlbumViewHolder(private val albumBinding: ItemAlbumBinding):RecyclerView.ViewHolder(albumBinding.root) {
        fun albumbind(item: Album)
        {
            albumBinding.albumNameTv.text = item.title
            val context = albumBinding.root.context
            albumBinding.root.setOnClickListener{
                context.startActivity(
                    Intent(context,PhotoActivity::class.java).apply {
                        putExtra("id",item.id)
                    }
                )

            }
        }

    }
}
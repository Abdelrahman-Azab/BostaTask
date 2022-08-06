package com.azab.bostaTask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.azab.bostaTask.adapter.AlbumAdapter
import com.azab.bostaTask.databinding.ActivityProfileBinding
import com.azab.bostaTask.model.Album
import com.azab.bostaTask.model.User
import com.azab.bostaTask.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var profileBinding: ActivityProfileBinding
    private val viewModel:ProfileViewModel  by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)
        dataObserve()
    }

    private fun dataObserve() {
        viewModel.getUsers()
        viewModel.usersResponseU.observe(this)
        {
            val users = it
            val randomNumber = Random.nextInt(10) //random from 1 to 10 to get one user when open activity and send it to albums
            setUserInTextView(users[randomNumber])
            viewModel.getAlbums(users[randomNumber].id)

        }
        viewModel.albumResponseA.observe(this)
        {
            val albums = it
            setAlbumsInRecyclerView(albums)
        }
    }

    private fun setUserInTextView(item: User)
    {
        runOnMainThread { profileBinding.usernameTv.text = item.name
            profileBinding.useraddressTv.text = item.address.street+","+item.address.city }

    }

    private fun setAlbumsInRecyclerView(items:List<Album>)
    {

        runOnMainThread { profileBinding.albumsRv.layoutManager = LinearLayoutManager(this)
            profileBinding.albumsRv.adapter = AlbumAdapter(items)}
    }
    private fun runOnMainThread(block:()-> Unit)
    {
        Handler(Looper.getMainLooper()).run {block()}
    }

}
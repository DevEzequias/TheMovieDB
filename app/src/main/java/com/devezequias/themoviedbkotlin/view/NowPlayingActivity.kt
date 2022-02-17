package com.devezequias.themoviedbkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.devezequias.themoviedbkotlin.R
import com.devezequias.themoviedbkotlin.databinding.ActivityNowPlayingBinding
import com.devezequias.themoviedbkotlin.view.adapter.NowPlayingAdapter
import com.devezequias.themoviedbkotlin.viewmodel.NowPlayingViewModel

class NowPlayingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNowPlayingBinding
    private var language: String = "pt-BR"
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        binding = ActivityNowPlayingBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_TheMovieDBKotlin)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        val viewModel: NowPlayingViewModel = ViewModelProviders.of(
            this@NowPlayingActivity)[NowPlayingViewModel::class.java]

        viewModel.nowPlaying.observe(this, Observer {
            it?.let { movies ->
                with(binding.recyclerNowPlaying) {
                    layoutManager = GridLayoutManager(this@NowPlayingActivity, 3)
                    setHasFixedSize(true)
                    adapter = NowPlayingAdapter(movies) { movieModel ->
                        val intent = MovieDetailsActivity.getStartIntent(
                            this@NowPlayingActivity,
                            movieModel.movie_id
                        )
                        startActivity(intent)
                    }
                }
            }
        })
        viewModel.getNowPlaying(language, page++)
    }
}
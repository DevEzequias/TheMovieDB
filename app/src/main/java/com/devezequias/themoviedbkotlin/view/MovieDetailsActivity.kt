package com.devezequias.themoviedbkotlin.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.devezequias.themoviedbkotlin.R
import com.devezequias.themoviedbkotlin.databinding.ActivityMovieDetailsBinding
import com.devezequias.themoviedbkotlin.model.entity.Details.DetailsModel
import com.devezequias.themoviedbkotlin.model.entity.Genres.GenreModel
import com.devezequias.themoviedbkotlin.model.service.Credentials
import com.devezequias.themoviedbkotlin.view.adapter.SimilarAdapter
import com.devezequias.themoviedbkotlin.viewmodel.DetailsViewModel
import com.devezequias.themoviedbkotlin.viewmodel.GenresViewModel
import com.devezequias.themoviedbkotlin.viewmodel.SimilarViewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private var page: Int = 1
    private var isFavorite: Boolean = false
    private var genresLoad: List<GenreModel> = listOf()
    private lateinit var movieDetails: DetailsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.backBtnDetails.setOnClickListener { onBackPressed() }

        changeButtonFavorite()
        getDetails()
        getGenresApi()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val viewModel: SimilarViewModel = ViewModelProviders.of(
            this)[SimilarViewModel::class.java]

        viewModel.getSimilar(movie_id, page++)

        viewModel.similar.observe(this, Observer {
            it?.let { movies ->
                with(binding.recyclerSimilar) {
                    layoutManager = LinearLayoutManager(this@MovieDetailsActivity)
                    setHasFixedSize(true)
                    adapter = SimilarAdapter(movies, genresLoad)
                }
            }
        })
    }

    private fun getGenresApi() {
        val viewModel: GenresViewModel = ViewModelProviders.of(
            this)[GenresViewModel::class.java]

        viewModel.getGenres()
        viewModel.genres.observe(this, Observer {
            it?.let { genres ->
                genresLoad = genres
            }
        })
    }

    private fun getDetails() {
        val viewModel: DetailsViewModel = ViewModelProviders.of(
            this)[DetailsViewModel::class.java]

        viewModel.getDetails(movie_id)

        viewModel.details.observe(this, Observer {
            it?.let { detailsMovies ->
                this.movieDetails = detailsMovies
                setupUI()
            }
        })
    }

    private fun setupUI() {
        binding.textTitleDetails.text = movieDetails.title
        binding.textPopularity.text = "Popularity: ${movieDetails.popularity}"
        binding.textVoteCount.text = "${movieDetails.vote_count} Likes"

        Glide.with(binding.root)
            .load(Credentials.BASE_URL_IMAGE + movieDetails.poster_path)
            .into(binding.imageViewDetails)
    }

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_ID"
        private var movie_id = 0

        fun getStartIntent(context: Context, movieId: Int) : Intent {
            return Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
                movie_id = movieId
            }
        }
    }

    private fun changeButtonFavorite() {
        val imgButton = binding.favBtn
        imgButton.setOnClickListener {
            isFavorite = !isFavorite
            if (!isFavorite) {
                imgButton.setImageResource(R.drawable.ic_fav_off)
            } else {
                imgButton.setImageResource(R.drawable.ic_fav_on)
            }
        }
    }
}
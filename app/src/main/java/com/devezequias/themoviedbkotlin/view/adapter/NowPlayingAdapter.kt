package com.devezequias.themoviedbkotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devezequias.themoviedbkotlin.databinding.MovieListLayoutBinding
import com.devezequias.themoviedbkotlin.model.entity.NowPlaying.MovieModel
import com.devezequias.themoviedbkotlin.model.service.Credentials

class NowPlayingAdapter(
    private val movies: List<MovieModel>,
    private val onItemClickListener: ((movie: MovieModel) -> Unit),
) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingAdapter.NowPlayingViewHolder {
        val binding = MovieListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NowPlayingViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bindView(movies[position])
    }

    override fun getItemCount() = movies.count()

    class NowPlayingViewHolder(
        private val binding: MovieListLayoutBinding,
        private val onItemClickListener: ((movie: MovieModel) -> Unit),
    ) : RecyclerView.ViewHolder(binding.root) {

        private val title = binding.movieTitle

        fun bindView(movie: MovieModel) {
            title.text = movie.title
            Glide.with(binding.root)
                .load(Credentials.BASE_URL_IMAGE + movie.poster_path)
                .into(binding.movieImage)

            itemView.setOnClickListener {
                onItemClickListener.invoke(movie)
            }
        }
    }


}
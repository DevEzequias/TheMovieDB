package com.devezequias.themoviedbkotlin.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devezequias.themoviedbkotlin.databinding.MovieListSimilarLayoutBinding
import com.devezequias.themoviedbkotlin.model.entity.Genres.GenreModel
import com.devezequias.themoviedbkotlin.model.entity.Similar.SimilarModel
import com.devezequias.themoviedbkotlin.model.service.Credentials
import kotlin.collections.ArrayList

class SimilarAdapter(
    private val movies: List<SimilarModel>,
    private val genres: List<GenreModel>,
) : RecyclerView.Adapter<SimilarAdapter.SimilarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val binding = MovieListSimilarLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return SimilarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bindView(movies[position], genres)
    }

    override fun getItemCount() = movies.count()

    class SimilarViewHolder(
        private val binding: MovieListSimilarLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val titleSimilar = binding.textTitleSimilar
        private val dateSimilar = binding.textOverviewYear
        private val textGenres = binding.textOverviewGenre
        private var viewNames = ArrayList<String>()

        fun bindView(movie: SimilarModel, genres: List<GenreModel>) {
            titleSimilar.text = movie.title
            dateSimilar.text = movie.release_date

            Glide.with(binding.root)
                .load(Credentials.BASE_URL_IMAGE + movie.poster_path)
                .into(binding.imageSimilar)

            viewNames.clear()
            for (item in movie.genres) {
                for (gen in genres) {
                    if (item == gen.id) {
                        viewNames.add(gen.name)
                        println(viewNames)
                        Log.i("mostrar Nomes após", "$viewNames")
                    }
                }
            }

            textGenres.text = viewNames.toString().removeSurrounding("[", "]")
        }
    }
}
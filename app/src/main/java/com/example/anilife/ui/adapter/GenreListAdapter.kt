package com.example.anilife.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.anilife.R
import com.example.anilife.databinding.AnimeGenreItemBinding
import com.example.anilife.vo.Genre

class GenreListAdapter constructor(private val genreList: List<Genre?>) :
    ListAdapter<Genre, GenreListAdapter.GenreViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Genre>() {

            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreViewHolder {
        val binding: AnimeGenreItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.anime_genre_item,
            parent,
            false
        )

        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position]!!)
    }

    override fun getItemCount(): Int {
        return if (genreList.size >= 2) {
            2
        } else {
            genreList.size
        }
    }

    class GenreViewHolder(private val binding: AnimeGenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.genre = genre
            binding.executePendingBindings()
        }
    }
}
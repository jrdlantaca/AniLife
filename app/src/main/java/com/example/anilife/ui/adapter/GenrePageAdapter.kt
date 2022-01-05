package com.example.anilife.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.anilife.R
import com.example.anilife.databinding.AnimeGenreItemBinding
import com.example.anilife.databinding.AnimePageGenreItemBinding
import com.example.anilife.vo.Genre


class GenrePageAdapter constructor(private val genreList: List<Genre?>?) :
    ListAdapter<Genre, GenrePageAdapter.GenrePageViewHolder>(DIFF_CALLBACK) {

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
    ): GenrePageViewHolder {
        val binding: AnimePageGenreItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.anime_page_genre_item,
            parent,
            false
        )
        return GenrePageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenrePageViewHolder, position: Int) {
        holder.bind(genreList?.get(position)!!)
    }


    override fun getItemCount(): Int {
        return genreList?.size ?: 0
    }

    class GenrePageViewHolder(private val binding: AnimePageGenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.genre = genre
            binding.executePendingBindings()
        }
    }
}
package com.example.anilife.ui.adapter


import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.*
import com.example.anilife.R
import com.example.anilife.databinding.AiringAnimeItemBinding
import com.example.anilife.vo.AiringAnime
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class AnimeListAdapter(private val onSelect: (AiringAnime?) -> Unit) : ListAdapter<AiringAnime, AnimeListAdapter.AnimeViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AiringAnime>() {
            override fun areItemsTheSame(oldItem: AiringAnime, newItem: AiringAnime): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: AiringAnime, newItem: AiringAnime): Boolean {
                return oldItem.image_url == newItem.image_url
            }
        }
    }
    private var displayMetrics = DisplayMetrics()
    private var height = 0
    private var width = 0

    private var animeList = ArrayList<AiringAnime>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding: AiringAnimeItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.airing_anime_item,
            parent,
            false
        )
        displayMetrics = parent.context.resources.displayMetrics
        height = displayMetrics.heightPixels / 3
        width = (displayMetrics.widthPixels / 2) - 50
        binding.animeImage.layoutParams.height = height
        binding.animeImage.layoutParams.width = width
        binding.animeTitle.layoutParams.width = width
        binding.animeGenreRecyclerview.layoutParams.height = binding.animeTitle.layoutParams.height

        val layoutManager = FlexboxLayoutManager(binding.root.context,FlexDirection.ROW)
        layoutManager.maxLine = 1
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.alignItems = AlignItems.CENTER
        binding.animeGenreRecyclerview.layoutManager = layoutManager

        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position], onSelect)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    fun setAnime(airingAnimes: List<AiringAnime>) {
        this.animeList = airingAnimes as ArrayList<AiringAnime>
        notifyDataSetChanged()
    }

    class AnimeViewHolder(private val binding: AiringAnimeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(airingAnime: AiringAnime, onSelect : (AiringAnime?) -> Unit) {
            val adapter = GenreListAdapter(airingAnime.genres!!)
            binding.animeGenreRecyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
            binding.airingAnime = airingAnime
            binding.root.setOnClickListener{
                onSelect(airingAnime)
            }
            binding.index = layoutPosition
            binding.executePendingBindings()
        }
    }
}
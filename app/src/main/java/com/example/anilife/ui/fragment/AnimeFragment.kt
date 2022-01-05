package com.example.anilife.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anilife.R
import com.example.anilife.databinding.AnimeFragmentBinding
import com.example.anilife.ui.adapter.GenrePageAdapter
import com.example.anilife.viewmodel.AnimeViewModel
import com.example.anilife.vo.Anime
import com.google.android.material.bottomnavigation.BottomNavigationView

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.anime_fragment.*
import kotlinx.coroutines.*

@AndroidEntryPoint
class AnimeFragment : Fragment(R.layout.anime_fragment) {

    private val animeViewModel : AnimeViewModel by activityViewModels()

    private var id: Int? = 1

    private var _binding: AnimeFragmentBinding? = null
    private val binding get() = _binding!!
    private var isLoading : Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.anime_fragment, container, false)

        val view = binding.root
        _binding!!.animeViewModel = animeViewModel
        id = requireArguments().getInt("mal_id")
        animeViewModel.setMalID(id!!)

        Log.d("TAG", "MAL ID : $id")

        val layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.animePageGenreRecyclerview.layoutManager = layoutManager

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            animeViewModel.anime(id!!).observe(viewLifecycleOwner, Observer { result ->
                bindAnime(result!!.data)
                
            })
            showPage()
        }
    }

    private suspend fun showPage() {
        delay(5000)
        anime_fragment_progress_bar!!.visibility = View.GONE
        anime_fragment_constraint_layout!!.visibility = View.VISIBLE
    }


    private fun bindAnime(anime: Anime?) {
        binding.anime = anime
        if (anime?.genres != null) {
            val adapter = GenrePageAdapter(anime.genres)
            adapter.notifyDataSetChanged()
            binding.animePageGenreRecyclerview.adapter = adapter
        }
        binding.executePendingBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
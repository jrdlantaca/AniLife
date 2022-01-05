package com.example.anilife.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anilife.R

import com.example.anilife.databinding.WinterFragmentBinding
import com.example.anilife.ui.adapter.AnimeListAdapter
import com.example.anilife.util.CardItemDecoration
import com.example.anilife.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.winter_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class WinterFragment : Fragment(R.layout.winter_fragment) {


    private val animeViewModel : AnimeViewModel by activityViewModels()
    private var _binding : WinterFragmentBinding? = null
    private val binding get() = _binding!!
    private var year : Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.winter_fragment, container, false)

        _binding!!.animeViewModel = animeViewModel
        val view = binding.root
        year = Calendar.getInstance().get(Calendar.YEAR)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AnimeListAdapter{
            airingAnime -> val bundle = Bundle()
            bundle.putInt("mal_id",airingAnime!!.mal_id!!)
            bundle.putString("title",airingAnime.title)
            findNavController().navigate(R.id.animeFragment,bundle)
        }
        val spaceItemDecoration = CardItemDecoration(45)
        winter_anime_recyclerview.layoutManager = LinearLayoutManager(context)
        winter_anime_recyclerview.setHasFixedSize(true)
        winter_anime_recyclerview.addItemDecoration(spaceItemDecoration)
        winter_anime_recyclerview.adapter = adapter


        CoroutineScope(Dispatchers.Main).launch {
            animeViewModel.winterAnime(year!!).observe(viewLifecycleOwner, Observer { result ->
                adapter.setAnime(result.data!!)
                requireActivity().progress_bar.visibility = View.INVISIBLE
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
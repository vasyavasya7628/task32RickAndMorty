package com.example.task32rickandmorty.presentaion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task32rickandmorty.databinding.FragmentHeroesBinding
import com.example.task32rickandmorty.model.HeroesViewModel
import timber.log.Timber

class HeroesFragment : Fragment() {

    private var _binding: FragmentHeroesBinding? = null
    private val binding get() = _binding!!
    private val heroesViewModel: HeroesViewModel by viewModels()
    private val heroesAdapter = HeroesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroesViewModel.heroesExport.observe(viewLifecycleOwner) {
            heroesAdapter.submitList(it)
            Timber.tag("Data To Adapter").d(it.toString())
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewHeroes.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewHeroes.adapter = heroesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
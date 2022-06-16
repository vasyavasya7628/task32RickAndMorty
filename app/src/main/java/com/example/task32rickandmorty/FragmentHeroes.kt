package com.example.task32rickandmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.task32rickandmorty.databinding.FragmentHeroesBinding

class FragmentHeroes : Fragment() {

    private var _binding: FragmentHeroesBinding? = null
    private val binding get() = _binding!!
    private val heroesViewModel: HeroesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}
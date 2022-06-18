package com.example.task32rickandmorty.presentaion

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task32rickandmorty.R
import com.example.task32rickandmorty.databinding.FragmentEpisodesBinding
import com.example.task32rickandmorty.databinding.FragmentHeroesBinding
import timber.log.Timber


class FragmentEpisodes : Fragment() {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private var getName: String = ""
    private var getEpisodes: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showEpisodes()
    }

    private fun showEpisodes() {
        Timber.tag("FragmentEpisodes").d(getName + getEpisodes)
        binding.textEpisodes.text = getName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("name")?.let {
            getName = it
        }
//        arguments?.apply {
//            getString("name")?.let {
//                getName = it
//            }
//
//            getString("episodes")?.let {
//                getEpisodes = it
//            }
//        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(sendName: String, sendEpisodes: String) = FragmentEpisodes.apply {
            var arguments = Bundle().apply {
                putString("name", sendName)
                putString("episodes", sendEpisodes)
            }
        }
    }
}
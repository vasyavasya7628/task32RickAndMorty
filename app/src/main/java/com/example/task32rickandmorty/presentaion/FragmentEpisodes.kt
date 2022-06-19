package com.example.task32rickandmorty.presentaion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.task32rickandmorty.databinding.FragmentEpisodesBinding
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
        binding.textName.text = getName
        binding.textEpisodes.text = getEpisodes
    }

    override fun onAttach(@NonNull context: Context) {
        super.onAttach(context)
        arguments?.getString("name")?.let {
            getName = it
        }

        arguments?.getString("episodes")?.let {
            getEpisodes = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(sendName: String, sendEpisodes: String): Fragment {
            return FragmentEpisodes().apply {
                arguments = Bundle().apply {
                    putString("name", sendName)
                    putString("episodes", sendEpisodes)
                    arguments
                }
            }
        }
    }

}
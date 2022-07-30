package com.example.task32rickandmorty.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.task32rickandmorty.databinding.FragmentEpisodesBinding
import timber.log.Timber

private const val NAME: String = "name"
private const val EP: String = "episodes"
private const val TAG: String = "FragmentEpisodes"

class FragmentEpisodes : Fragment() {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding !!
    private var getName: String = ""
    private var getEpisodes: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showEpisodes()
    }

    private fun showEpisodes() {
        Timber.tag(TAG).d(getName + getEpisodes)
        binding.tvName.text = getName
        binding.tvEpisodes.text = getEpisodes
    }

    override fun onAttach(@NonNull context: Context) {
        super.onAttach(context)
        arguments?.getString(NAME)?.let { name ->
            getName = name
        }

        arguments?.getString(EP)?.let { episodes ->
            getEpisodes = episodes
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(sendName: String, sendEpisodes: String): Fragment {
            return FragmentEpisodes().apply {
                arguments = Bundle().apply {
                    putString(NAME, sendName)
                    putString(EP, sendEpisodes)
                    arguments
                }
            }
        }
    }
}
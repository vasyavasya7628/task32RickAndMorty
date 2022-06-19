package com.example.task32rickandmorty.presentaion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task32rickandmorty.R
import com.example.task32rickandmorty.databinding.ItemButtonBinding
import com.example.task32rickandmorty.databinding.ItemHeroesBinding
import com.example.task32rickandmorty.model.HeroesUi

class HeroesAdapter(private val onClickNextPage: () -> Unit) :
    ListAdapter<HeroesUi, RecyclerView.ViewHolder>(diffUtil) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HeroesUi.Data -> 0
            is HeroesUi.NextPage -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> HeroesViewHolder(
                ItemHeroesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            1 -> PageViewHolder(
                ItemButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> {
                throw Throwable("ERROR")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HeroesViewHolder -> holder.bind(getItem(position) as HeroesUi.Data)
            is PageViewHolder -> holder.bind(onClickNextPage)

        }
    }
}

class HeroesViewHolder(private val binding: ItemHeroesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HeroesUi.Data) {
        binding.textSpecies.text = item.item.species
        binding.textGender.text = item.item.gender

        Glide.with(binding.imgHero.context)
            .load(item.item.image)
            .into(binding.imgHero)

        binding.imgHero.setOnClickListener {
            val fragmentEpisodes: FragmentEpisodes =
                FragmentEpisodes.newInstance(
                    item.item.name,
                    item.item.episode.toString()
                ) as FragmentEpisodes
            val transaction = itemView.context as AppCompatActivity
            transaction.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, fragmentEpisodes)
                .addToBackStack(null)
                .commit()
        }
    }
}

class PageViewHolder(private val binding: ItemButtonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(onClickNextPage: () -> Unit) {
        binding.btLoad.setOnClickListener {
            onClickNextPage()
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<HeroesUi>() {

    override fun areItemsTheSame(
        oldItem: HeroesUi,
        newItem: HeroesUi
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: HeroesUi,
        newItem: HeroesUi
    ): Boolean {
        return oldItem == newItem
    }
}
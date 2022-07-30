package com.example.task32rickandmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task32rickandmorty.R
import com.example.task32rickandmorty.databinding.ItemHeroesBinding

class HeroesAdapter : ListAdapter<HeroesUI, ItemViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemHeroesBinding
                .inflate(LayoutInflater
                    .from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position) as HeroesUI)
    }
}

class ItemViewHolder(private val binding: ItemHeroesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HeroesUI) {
        binding.tvRace.text = item.species
        binding.tvGender.text = item.gender

        Glide.with(binding.ivHero.context)
            .load(item.image)
            .into(binding.ivHero)

        binding.ivHero.setOnClickListener {

            val fragmentEpisodes: FragmentEpisodes =
                FragmentEpisodes.newInstance(item.name, item.episode.toString()) as FragmentEpisodes
            val transaction = itemView.context as AppCompatActivity
            transaction.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, fragmentEpisodes)
                .addToBackStack(null)
                .commit()
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<HeroesUI>() {

    override fun areItemsTheSame(oldItem: HeroesUI, newItem: HeroesUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HeroesUI, newItem: HeroesUI): Boolean {
        return oldItem == newItem
    }
}
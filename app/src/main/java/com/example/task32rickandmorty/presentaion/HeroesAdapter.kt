package com.example.task32rickandmorty.presentaion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task32rickandmorty.R
import com.example.task32rickandmorty.data.HeroesLocal
import com.example.task32rickandmorty.databinding.ItemHeroesBinding

class HeroesAdapter : ListAdapter<HeroesLocal, ItemViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemHeroesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position) as HeroesLocal)
    }
}

class ItemViewHolder(binding: ItemHeroesBinding) : RecyclerView.ViewHolder(binding.root) {
    private val bindItem = binding
    fun bind(item: HeroesLocal) {
        bindItem.textSpecies.text = item.species
        bindItem.textGender.text = item.gender

        Glide.with(bindItem.imgHero.context)
            .load(item.image)
            .into(bindItem.imgHero)
        bindItem.imgHero.setOnClickListener{
            //список обработать GSON
            val fragmentEpisodes = FragmentEpisodes.newInstance(item.name, item.episode.toString())

            val transaction = itemView.context as AppCompatActivity
            transaction.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, FragmentEpisodes())
                .addToBackStack(null)
                .commit()
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<HeroesLocal>() {

    override fun areItemsTheSame(oldItem: HeroesLocal, newItem: HeroesLocal): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HeroesLocal, newItem: HeroesLocal): Boolean {
        return oldItem == newItem
    }
}
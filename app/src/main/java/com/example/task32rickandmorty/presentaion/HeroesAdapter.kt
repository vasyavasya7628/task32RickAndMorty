package com.example.task32rickandmorty.presentaion

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task32rickandmorty.data.HeroesLocal
import com.example.task32rickandmorty.databinding.ItemHeroesBinding
import timber.log.Timber
import java.security.AccessController.getContext

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
            //itemView.context.startActivity()
            Toast.makeText(itemView.context, "TEXT", Toast.LENGTH_SHORT).show()
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
package com.example.ta_ppb1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ta_ppb1.databinding.ItemListRecipeBinding
import com.example.ta_ppb1.entity.Recipe

class RecipesAdapter(private val recipes: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private lateinit var binding: ItemListRecipeBinding

    inner class ViewHolder(private val binding: ItemListRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = recipes[position]
    }

}
package com.example.ta_ppb1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ta_ppb1.databinding.ItemListRecipeBinding
import com.example.ta_ppb1.entity.RecipeWithAuthor

class RecipesAdapter(
    private val recipes: ArrayList<RecipeWithAuthor>,
    private val listener: Events
) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private lateinit var binding: ItemListRecipeBinding

    interface Events {
        fun onClick(recipe: RecipeWithAuthor)
    }

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
        binding.title.text = item.name
        binding.creator.text = item.authorName

        binding.parentCard.setOnClickListener {
            listener.onClick(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun dispatch(data: ArrayList<RecipeWithAuthor>) {
        recipes.clear()
        recipes.addAll(data)
        notifyDataSetChanged()
    }

}
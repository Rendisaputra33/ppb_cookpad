package com.example.ta_ppb1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ta_ppb1.databinding.ItemMyRecipeBinding
import com.example.ta_ppb1.entity.RecipeWithAuthor

class MyRecipeAdapter(
    private val recipes: ArrayList<RecipeWithAuthor>,
    private val listener: Events
) :
    RecyclerView.Adapter<MyRecipeAdapter.ViewHolder>() {

    private lateinit var binding: ItemMyRecipeBinding

    interface Events {
        fun onEdit(recipe: RecipeWithAuthor)
        fun onDelete(recipe: RecipeWithAuthor)
    }

    inner class ViewHolder(private val binding: ItemMyRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMyRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = recipes[position]
        binding.judul.text = item.name
        binding.tanggal.text = item.authorName

        binding.btnEdit.setOnClickListener {
            listener.onEdit(item)
        }

        binding.btnDelete.setOnClickListener {
            listener.onDelete(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun dispatch(data: ArrayList<RecipeWithAuthor>) {
        recipes.clear()
        recipes.addAll(data)
        notifyDataSetChanged()
    }

}

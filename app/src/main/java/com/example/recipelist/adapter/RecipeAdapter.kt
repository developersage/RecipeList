package com.example.recipelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelist.model.Recipe
import com.example.recipelist.databinding.ItemRecipeBinding

class RecipeAdapter(
    private val recipe: List<Recipe>,
    private val selectedRecipe: (Recipe) -> Unit
): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = RecipeViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener { selectedRecipe(recipe[adapterPosition]) }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.loadInfo(recipe[position])
    }

    override fun getItemCount() = recipe.size

    class RecipeViewHolder(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadInfo(recipe: Recipe) = with(binding) {
            tvId.text = recipe.id.toString()
            tvName.text = recipe.recipeName
            tvDesc.text = recipe.description
            tvIngr.text = recipe.ingredients
        }

        companion object {
            fun getInstance(parent: ViewGroup) = ItemRecipeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).run{ RecipeViewHolder(this) }
        }

    }
}
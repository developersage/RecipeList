package com.example.recipelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipelist.model.Recipe
import com.example.recipelist.repo.RecipeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RecipeViewModel(
    private val recipeRepo: RecipeRepo
): ViewModel() {

    val recipes = recipeRepo.getAllRecipes().asLiveData(
        viewModelScope.coroutineContext + Dispatchers.Default
    )

    fun addRecipe(item: Recipe) {
        viewModelScope.launch {
            recipeRepo.addRecipe(item)
        }
    }

    fun deleteRecipe(item: Recipe) {
        viewModelScope.launch {
            recipeRepo.deleteRecipe(item)
        }
    }

    class Factory(
        private val recipeRepo: RecipeRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipeViewModel::class.java)){
                return RecipeViewModel(recipeRepo) as T
            }else {
                throw IllegalArgumentException("Cannot create instance of RecipeViewModel")
            }
        }
    }

}
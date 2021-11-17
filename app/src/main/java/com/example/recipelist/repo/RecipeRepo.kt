package com.example.recipelist.repo

import android.app.Application
import com.example.recipelist.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RecipeRepo(private val application: Application) {

    private val recipeDao by lazy {
        RecipeDatabase.getDatabase(application).RecipeDao()
    }

    fun getAllRecipes() = recipeDao.getAllRecipes().flowOn(Dispatchers.IO)

    suspend fun addRecipe(recipe: Recipe) {
        withContext(Dispatchers.IO){
            recipeDao.insertRecipe(recipe)
        }
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        withContext(Dispatchers.IO){
            recipeDao.deleteRecipe(recipe)
        }
    }

}
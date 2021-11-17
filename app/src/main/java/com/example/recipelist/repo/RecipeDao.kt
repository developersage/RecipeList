package com.example.recipelist.repo

import androidx.room.*
import com.example.recipelist.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)

    @Insert
    fun insertAllRecipes(vararg recipes: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)
}
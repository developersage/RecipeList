package com.example.recipelist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @ColumnInfo(name = "recipe_name") val recipeName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "ingredients") val ingredients: String,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
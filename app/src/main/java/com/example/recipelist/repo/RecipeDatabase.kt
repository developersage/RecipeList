package com.example.recipelist.repo

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipelist.model.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun RecipeDao(): RecipeDao

    companion object {
        const val DATABASE_NAME = "recipe_database"

        private lateinit var application: Application

        private val database: RecipeDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(application, RecipeDatabase::class.java, DATABASE_NAME).build()
        }

        fun getDatabase(new_application: Application): RecipeDatabase {
            application = new_application
            return database
        }

    }
}
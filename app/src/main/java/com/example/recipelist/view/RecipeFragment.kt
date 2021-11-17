package com.example.recipelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipelist.model.Recipe
import com.example.recipelist.repo.RecipeRepo
import com.example.recipelist.viewmodel.RecipeViewModel
import com.example.recipelist.adapter.RecipeAdapter
import com.example.recipelist.databinding.FragmentRecipeBinding

class RecipeFragment: Fragment() {
    //private val viewModel: RecipeViewModel by activityViewModels<RecipeViewModel>()

    private val viewModel: RecipeViewModel by viewModels {
        RecipeViewModel.Factory(RecipeRepo(requireActivity().application))
    }

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSaveBtn()
        viewModel.recipes.observe(viewLifecycleOwner) { recipeList ->
            binding.rvRecipeList.adapter = RecipeAdapter(recipeList, ::recipeSelected)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpSaveBtn() = with(binding) {
        btnSubmit.setOnClickListener {
            viewModel.addRecipe(
                Recipe(etRecipeName.text.toString(),
                    etDescription.text.toString(),
                    etIngredients.text.toString()
                )
            )
        }
    }

    private fun recipeSelected(recipe: Recipe) {
        binding.btnDelete.setOnClickListener {
            viewModel.deleteRecipe(recipe)
        }
    }

}
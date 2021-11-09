package com.mango.movies.ui.categoris

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mango.movies.R
import com.mango.movies.databinding.FragmentCategoryBinding
import com.mango.movies.ui.base.BaseFragment
import com.mango.movies.ui.home.HomeFragmentDirections
import com.mango.movies.util.Constant
import com.mango.movies.util.State

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {
    override val LOG_TAG: String = Constant.CATEGORY_FRAGMENT
    override val viewModel: ResultViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoryBinding =
        DataBindingUtil::inflate

    override fun setupView() {
        binding.viewModel=viewModel
        binding.recyclerMovies.adapter=MovieAndTvResultAdapter(mutableListOf(), viewModel)
        binding.recyclerGenre.adapter=GenreAdapter(mutableListOf(), viewModel)

        viewModel.selectedMovie?.observe(viewLifecycleOwner){
            if (it is State.Success){
                val action = CategoryFragmentDirections.actionCategoryFragmentToDetailsFragment(requireNotNull(it.toData()))
                Navigation.findNavController(requireView()).navigate(action)
            }
        }

        viewModel.selectSeries?.observe(viewLifecycleOwner){
            if (it is State.Success){
                val action = CategoryFragmentDirections.actionCategoryFragmentToDetailsSeriesFragment(requireNotNull(it.toData()))
                Navigation.findNavController(requireView()).navigate(action)
            }
        }
    }

}
package com.mango.movies.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mango.movies.model.domain.BaseResponse
import com.mango.movies.model.domain.Movie
import com.mango.movies.model.repositiory.MovieRepository
import com.mango.movies.ui.movie.MovieInteractionListener
import com.mango.movies.util.State
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class SearchViewModel : ViewModel(), MovieInteractionListener {
    var searchResult = MutableLiveData<State<BaseResponse<Movie>?>?>()
    var selectedMovie =  MutableLiveData<Movie?>()
    var flag = MutableLiveData<Boolean>()

    override fun onClickMovie(movie: Movie) {
        selectedMovie.postValue(movie)
    }

    fun onTextChanged(text: CharSequence?) {
        flag.postValue(false)
        viewModelScope.launch {
            MovieRepository.searchMovie(text.toString()).collect {
                searchResult.postValue(it)
            }
        }
    }

}
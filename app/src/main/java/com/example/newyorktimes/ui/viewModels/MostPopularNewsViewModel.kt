package com.example.newyorktimes.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newyorktimes.data.models.News
import com.example.newyorktimes.data.network.repository.MostPopularNewsRepository
import com.example.newyorktimes.utlis.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MostPopularNewsViewModel(
    private val mostPopularNewsRepository: MostPopularNewsRepository
) : ViewModel() {

    private val _newsResult = MutableLiveData<Resource<List<News>>>()
    val newsResult: LiveData<Resource<List<News>>>
        get() = _newsResult

    fun getMostPopularNews() {
        _newsResult.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                mostPopularNewsRepository.getMostPopularNews(PERIOD)
            }.onSuccess { newsResult ->
                if (newsResult.results.isNullOrEmpty()) {
                    _newsResult.postValue(Resource.error())
                } else {
                    _newsResult.postValue(Resource.success(newsResult.results))
                }
            }.onFailure {
                Timber.e(it)
                _newsResult.postValue(Resource.error())
            }
        }
    }

    companion object {
        const val PERIOD = "7"
    }
}
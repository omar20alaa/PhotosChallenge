package app.photos_challenge.app.presentation.viewmodel

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.app.utils.NetworkUtils.Companion.isInternetAvailable
import app.photos_challenge.domain.usecase.FetchAndCachePhotosUseCase
import app.photos_challenge.domain.usecase.GetCachedPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject
constructor(
    private val fetchAndCachePhotosUseCase: FetchAndCachePhotosUseCase,
    private val getCachedPhotosUseCase: GetCachedPhotosUseCase
) : ViewModel() {

    private val _photos = MutableLiveData<List<FlickrPhoto>>()
    val photos: LiveData<List<FlickrPhoto>> = _photos
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private var currentPage = 1
    private var isFetching = false

    fun fetchPhotos(connectivityManager: ConnectivityManager) {
        if (isInternetAvailable(connectivityManager) && !isFetching) {
            isFetching = true
            _isLoading.postValue(true)
            viewModelScope.launch {
                val result = fetchAndCachePhotosUseCase.invoke(currentPage)
                _isLoading.postValue(false)
                if (result.isSuccess) {
                    val fetchedPhotos = result.getOrDefault(emptyList())
                    val currentPhotos = _photos.value.orEmpty()
                    val updatedPhotos = currentPhotos + fetchedPhotos
                    _photos.postValue(updatedPhotos)
                    if (fetchedPhotos.isNotEmpty()) {
                        currentPage++
                    }
                }
                isFetching = false
            }
        } else {
            getCachedPhotos()
        }
    }

    fun fetchNextPage(connectivityManager: ConnectivityManager) {
        fetchPhotos(connectivityManager)
    }

    private fun getCachedPhotos() {
        viewModelScope.launch {
            val cachedPhotos = getCachedPhotosUseCase.invoke().first()
            _photos.postValue(cachedPhotos)
        }
    }
}
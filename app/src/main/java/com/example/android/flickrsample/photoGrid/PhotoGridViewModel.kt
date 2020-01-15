package com.example.android.flickrsample.photoGrid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.flickrsample.models.Photo
import com.example.android.flickrsample.network.FlickrApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhotoGridViewModel(application: Application) : AndroidViewModel(application) {

    enum class FlickrApiStatus { LOADING, ERROR, DONE }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _status = MutableLiveData<FlickrApiStatus>()
    val status: LiveData<FlickrApiStatus>
        get() = _status

    private val _navigateToSelectedPhoto = MutableLiveData<Photo>()
    val navigateToSelectedPhoto: LiveData<Photo>
        get() = _navigateToSelectedPhoto

    init {
        getPhotos()
    }

    private fun getPhotos() {
        coroutineScope.launch {
            val getPhotosDeferred = FlickrApi.retrofitService.getPhotos()
            try {
                _status.value = FlickrApiStatus.LOADING

                val listResult = getPhotosDeferred.await()
                _status.value = FlickrApiStatus.DONE

                if (listResult.photos.photo.isNotEmpty()) {
                    _photos.value = listResult.photos.photo
                }
            } catch (e: Exception) {
                _status.value = FlickrApiStatus.ERROR
            }
        }
    }
}
package ru.netology.coinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.netology.coinapp.dto.Asset
import ru.netology.coinapp.error.AppError
import ru.netology.coinapp.repository.AssetRepository
import javax.inject.Inject

@HiltViewModel
class AssetViewModel @Inject constructor(
    private val repository: AssetRepository
) : ViewModel() {
    val assets: LiveData<List<Asset>> = repository.data.asLiveData(Dispatchers.Default)

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    init {
        loadAssets()
    }

    fun loadAssets()  = viewModelScope.launch {
        _error.value = null
        _loading.value = true
        try {
            repository.load()
        } catch (e: Exception) {
            _error.value = AppError.from(e).code
        } finally {
            _loading.value = false
        }
    }
}
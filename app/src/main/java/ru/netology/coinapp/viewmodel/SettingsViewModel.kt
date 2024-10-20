package ru.netology.coinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.netology.coinapp.repository.SettingsRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {
    val theme: LiveData<SettingsRepository.Theme> = repository.theme.asLiveData(Dispatchers.Default)

    fun setTheme(theme: SettingsRepository.Theme) {
        viewModelScope.launch {
            repository.setTheme(theme)
        }
    }
}
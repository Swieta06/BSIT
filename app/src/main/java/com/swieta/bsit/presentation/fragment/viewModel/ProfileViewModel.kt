package com.swieta.bsit.presentation.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swieta.bsit.domain.useCase.GetProfileUseCase
import com.swieta.bsit.model.ProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

    private val getProfileUseCase: GetProfileUseCase

) : ViewModel() {

    private val _profile = MutableLiveData<ProfileResponse>()
    val profile: LiveData<ProfileResponse>
        get() = _profile
    private val _errorMassage = MutableLiveData<String>()
    val errorMassage: LiveData<String>
        get() = _errorMassage

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    fun getProfile() = viewModelScope.launch {
        _showLoading.postValue(true)
        getProfileUseCase.getProfile().let {
            if (it.isSuccessful) {
                _profile.postValue(it.body())
                _showLoading.postValue(false)

            } else {
                _errorMassage.postValue(it.message())
                _showLoading.postValue(false)

            }
        }
    }

}
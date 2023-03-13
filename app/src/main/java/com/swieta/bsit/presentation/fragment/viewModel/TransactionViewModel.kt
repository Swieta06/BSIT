package com.swieta.bsit.presentation.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swieta.bsit.domain.useCase.GetTransactionUseCase
import com.swieta.bsit.model.TransactionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(

    private val getTransactionUseCase: GetTransactionUseCase

) : ViewModel() {


    private val _transaction = MutableLiveData<List<TransactionResponse>>()
    val transaction: LiveData<List<TransactionResponse>>
        get() = _transaction
    private val _errorMassage = MutableLiveData<String>()
    val errorMassage: LiveData<String>
        get() = _errorMassage

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    fun getProfile() = viewModelScope.launch {
        _showLoading.postValue(true)
        getTransactionUseCase.getTransaction().let {
            if (it.isSuccessful) {
                _transaction.postValue(it.body())
                _showLoading.postValue(false)

            } else {
                _errorMassage.postValue(it.message())
                _showLoading.postValue(false)

            }
        }
    }

}
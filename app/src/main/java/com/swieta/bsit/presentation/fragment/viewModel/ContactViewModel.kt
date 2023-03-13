package com.swieta.bsit.presentation.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swieta.bsit.domain.useCase.GetContactUseCase
import com.swieta.bsit.model.ContactResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(

    private val getContactUseCase: GetContactUseCase

) : ViewModel() {

    private val _contact = MutableLiveData<List<ContactResponse>>()
    val contact: LiveData<List<ContactResponse>>
        get() = _contact
    private val _errorMassage = MutableLiveData<String>()
    val errorMassage: LiveData<String>
        get() = _errorMassage

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading
    private var dummyContact: MutableList<ContactResponse> = mutableListOf()
    fun getContact() = viewModelScope.launch {
        _showLoading.postValue(true)
        getContactUseCase.getContact().let {
            if (it.isSuccessful) {
                dummyContact.addAll(it.body()?: mutableListOf())
                _contact.postValue(it.body())
                _showLoading.postValue(false)

            } else {
                _errorMassage.postValue(it.message())
                _showLoading.postValue(false)

            }
        }
    }

    fun filterSearchContact(text: String) {
        if(text.isEmpty()){
            _contact.postValue(dummyContact)
        }else{
            val filterData = dummyContact.filter { dataContact ->
                dataContact.name?.contains(text, true) ?: false

            }
            _contact.postValue(filterData)

        }


    }
}
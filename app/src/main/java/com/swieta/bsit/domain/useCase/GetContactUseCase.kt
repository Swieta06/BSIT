package com.swieta.bsit.domain.useCase

import com.swieta.bsit.domain.repository.Repository
import com.swieta.bsit.model.ContactResponse
import retrofit2.Response
import javax.inject.Inject

class GetContactUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getContact(): Response<List<ContactResponse>> {
        return repository.getContact()
    }
}
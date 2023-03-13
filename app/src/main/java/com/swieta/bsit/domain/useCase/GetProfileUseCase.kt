package com.swieta.bsit.domain.useCase

import com.swieta.bsit.domain.repository.Repository
import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.model.ProfileResponse
import retrofit2.Response
import javax.inject.Inject

class GetProfileUseCase@Inject constructor(
    private val repository: Repository
) {
    suspend fun getProfile(): Response<ProfileResponse> {
        return repository.getProfile()
    }
}
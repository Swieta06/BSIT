package com.swieta.bsit.domain.repository

import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.model.ProfileResponse
import com.swieta.bsit.model.TransactionResponse
import retrofit2.Response

interface Repository {
    suspend fun getTransaction(): Response<List<TransactionResponse>>
    suspend fun getContact(): Response<List<ContactResponse>>
    suspend fun getProfile(): Response<ProfileResponse>
}
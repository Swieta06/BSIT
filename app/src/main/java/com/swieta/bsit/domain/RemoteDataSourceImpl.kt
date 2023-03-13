package com.swieta.bsit.domain

import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.model.ProfileResponse
import com.swieta.bsit.model.TransactionResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: Service

) : RemoteDataSource {
    override suspend fun getTransaction(): Response<List<TransactionResponse>> {
        return service.getTransaction()
    }

    override suspend fun getContact(): Response<List<ContactResponse>> {
        return service.getContact()
    }

    override suspend fun getProfile(): Response<ProfileResponse> {
        return service.getProfile()
    }
}
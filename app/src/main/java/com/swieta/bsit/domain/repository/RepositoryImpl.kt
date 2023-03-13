package com.swieta.bsit.domain.repository

import com.swieta.bsit.domain.RemoteDataSource
import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.model.ProfileResponse
import com.swieta.bsit.model.TransactionResponse
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource:RemoteDataSource
):Repository {
    override suspend fun getTransaction(): Response<List<TransactionResponse>> {
        return remoteDataSource.getTransaction()
    }

    override suspend fun getContact(): Response<List<ContactResponse>> {
        return remoteDataSource.getContact()
    }

    override suspend fun getProfile(): Response<ProfileResponse> {
       return remoteDataSource.getProfile()
    }

}
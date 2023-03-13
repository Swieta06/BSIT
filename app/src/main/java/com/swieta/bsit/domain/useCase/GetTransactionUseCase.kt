package com.swieta.bsit.domain.useCase

import com.swieta.bsit.domain.repository.Repository
import com.swieta.bsit.model.TransactionResponse
import retrofit2.Response
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getTransaction(): Response<List<TransactionResponse>> {
        return repository.getTransaction()
    }
}
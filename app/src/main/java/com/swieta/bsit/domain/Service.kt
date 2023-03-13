package com.swieta.bsit.domain

import com.swieta.bsit.model.ContactResponse
import com.swieta.bsit.model.ProfileResponse
import com.swieta.bsit.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    //https://private-54eacf-fazztrack.apiary-mock.com/questions
//add list
    @GET("transaction")
    suspend fun getTransaction(): Response<List<TransactionResponse>>

    @GET("contact")
    suspend fun getContact(): Response<List<ContactResponse>>

    @GET("profile")
    suspend fun getProfile(): Response<ProfileResponse>

}
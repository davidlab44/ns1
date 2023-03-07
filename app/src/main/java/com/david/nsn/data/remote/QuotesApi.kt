package com.david.nsn.data.remote

import com.david.nsn.data.local.QuoteList
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
}
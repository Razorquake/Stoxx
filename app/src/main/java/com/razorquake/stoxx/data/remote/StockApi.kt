package com.razorquake.stoxx.data.remote

import com.razorquake.stoxx.data.remote.dto.StockOverviewResponse
import com.razorquake.stoxx.data.remote.dto.StockQuoteResponse
import com.razorquake.stoxx.data.remote.dto.StockSearchResponse
import com.razorquake.stoxx.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("query")
    suspend fun getStockQuote(
        @Query("function") function: String = "GLOBAL_QUOTE",
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): StockQuoteResponse

    @GET("query")
    suspend fun searchStocks(
        @Query("function") function: String = "SYMBOL_SEARCH",
        @Query("keywords") query: String,
        @Query("apikey") apiKey: String = API_KEY
    ): StockSearchResponse

    @GET("query")
    suspend fun getStockOverview(
        @Query("function") function: String = "OVERVIEW",
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): StockOverviewResponse
}
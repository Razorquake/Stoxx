package com.razorquake.stoxx.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.razorquake.stoxx.domain.GlobalQuote

data class StockQuoteResponse(
    @SerializedName("Global Quote")
    val globalQuote: GlobalQuote
)
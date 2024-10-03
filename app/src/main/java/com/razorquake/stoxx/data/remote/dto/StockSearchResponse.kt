package com.razorquake.stoxx.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.razorquake.stoxx.domain.BestMatch

data class StockSearchResponse(
    @SerializedName("bestMatches")
    val bestMatches: List<BestMatch>
)
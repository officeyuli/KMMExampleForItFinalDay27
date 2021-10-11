package com.officeyuli.kmmexampleforitfinal.ktor

import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem

interface CafeApi {
    suspend fun fetchCafeFromApi(city:String): List<CafeResponseItem>
}
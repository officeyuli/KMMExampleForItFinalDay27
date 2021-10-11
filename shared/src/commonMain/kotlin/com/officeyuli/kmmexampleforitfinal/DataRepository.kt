package com.officeyuli.kmmexampleforitfinal

import com.officeyuli.kmmexampleforitfinal.ktor.CafeApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DataRepository :KoinComponent{

    private val ktorApi:CafeApi by inject()
    suspend fun fetchCafesFromNetwork(cityName: String) =ktorApi.fetchCafeFromApi(cityName)
}
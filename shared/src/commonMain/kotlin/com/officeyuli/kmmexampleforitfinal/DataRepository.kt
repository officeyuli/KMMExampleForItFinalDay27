package com.officeyuli.kmmexampleforitfinal

import com.officeyuli.kmmexampleforitfinal.ktor.CafeApi
import com.officeyuli.kmmexampleforitfinal.ktor.CafeApiImpl

class DataRepository {

    private val ktorApi:CafeApi by lazy{
        CafeApiImpl()
    }
    suspend fun fetchCafesFromNetwork(cityName: String) =ktorApi.fetchCafeFromApi(cityName)
}
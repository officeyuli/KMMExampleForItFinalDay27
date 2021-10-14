package com.officeyuli.kmmexampleforitfinal

import com.officeyuli.kmmexampleforitfinal.ktor.CafeApi
import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem
import com.officeyuli.kmmexampleforitfinal.sqldelight.DatabaseHelper
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DataRepository :KoinComponent {

    private val ktorApi: CafeApi by inject()
    private val dbHelper: DatabaseHelper by inject()

    suspend fun fetchCafesFromNetwork(cityName: String) = ktorApi.fetchCafeFromApi(cityName)
    fun getCafeFromDb(): Flow<List<CAFE>> = dbHelper.selectAllItems()
    suspend fun insertCafeToDB(cafeResponse: List<CafeResponseItem>) {
        dbHelper.insertCafeList(cafeResponse)
    }

    suspend fun deleteCafeFromDB() {
        dbHelper.deleteALLCafe()
    }
}
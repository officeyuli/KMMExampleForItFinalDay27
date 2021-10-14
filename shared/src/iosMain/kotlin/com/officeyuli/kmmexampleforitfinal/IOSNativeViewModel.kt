package com.officeyuli.kmmexampleforitfinal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IOSNativeViewModel(
    private val onDataState: (List<CAFE>) -> Unit
) : KoinComponent {
    private val scope = IOSMainScope(Dispatchers.Main)
    private val dataRepository: DataRepository by inject()

    init {
        observeCafeList()
    }

    private fun observeCafeList() {
        scope.launch {
            dataRepository.getCafeFromDb().collect { dataState ->
                onDataState(dataState)
            }
        }
    }

    public fun fetchCafeListFromNewWork(cityName: String = "taipei") {
        scope.launch {
            val result = async { dataRepository.fetchCafesFromNetwork(cityName) }
            dataRepository.insertCafeToDB(result.await())
        }
    }

    fun onDestroy() {
        scope.onDestroy()
    }
}
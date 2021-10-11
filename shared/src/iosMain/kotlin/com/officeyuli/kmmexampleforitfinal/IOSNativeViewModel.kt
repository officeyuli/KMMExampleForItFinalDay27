package com.officeyuli.kmmexampleforitfinal

import com.officeyuli.kmmexampleforitfinal.IOSMainScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IOSNativeViewModel() : KoinComponent {
    private val scope = IOSMainScope(Dispatchers.Main)
    private val dataRepository: DataRepository by inject()

    public fun fetchCafeListFromNewWork(cityName: String = "taipei") {
        scope.launch {
            val result = async { dataRepository.fetchCafesFromNetwork(cityName) }
        }
    }

    fun onDestroy() {
        scope.onDestroy()
    }
}
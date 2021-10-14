package com.officeyuli.kmmexampleforitfinal.android

import androidx.lifecycle.*
import com.officeyuli.kmmexampleforitfinal.CAFE
import com.officeyuli.kmmexampleforitfinal.DataRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    private val dataRepository: DataRepository by inject()

    private val cafeList = MutableLiveData<List<CAFE>>()
    val cafeListLiveData: LiveData<List<CAFE>> = Transformations.map(cafeList) { it }

    fun fetchCafeList(cityName: String = "taipei") {
        viewModelScope.launch {
            val response = async { dataRepository.fetchCafesFromNetwork(cityName) }
            val result = response.await()
            dataRepository.deleteCafeFromDB()
            dataRepository.insertCafeToDB(result)
        }
    }

    fun fetchCafeFromDB() {
        viewModelScope.launch {
            dataRepository.getCafeFromDb().collect {
                cafeList.value = it
            }
        }
    }
}
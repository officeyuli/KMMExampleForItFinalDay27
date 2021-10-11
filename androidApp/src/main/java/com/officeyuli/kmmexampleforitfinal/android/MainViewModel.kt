package com.officeyuli.kmmexampleforitfinal.android

import androidx.lifecycle.*
import com.officeyuli.kmmexampleforitfinal.DataRepository
import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    private val dataRepository: DataRepository by inject()

    private val cafeListMutableLiveData = MutableLiveData<List<CafeResponseItem>>()
    val cafeListLiveData: LiveData<List<CafeResponseItem>> = Transformations.map(cafeListMutableLiveData) { it }


    fun fetchCafeList(cityName :String = "taipei"){
        viewModelScope.launch {
            val response = async {  dataRepository.fetchCafesFromNetwork(cityName)}
            val result = response.await()
            cafeListMutableLiveData.value = result
        }
    }
}
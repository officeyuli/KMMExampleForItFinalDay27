package com.officeyuli.kmmexampleforitfinal.mock

import com.officeyuli.kmmexampleforitfinal.ktor.CafeApi
import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class CafeApiMock(mockEngine: HttpClientEngine) : CafeApi {
    companion object {
        val tag = CafeApiMock::class.simpleName
    }

    private val client = HttpClient(mockEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    override suspend fun fetchCafeFromApi(city: String): List<CafeResponseItem> {
        return client.get("https://cafenomad.tw/api/v1.2/cafes/")
    }
}
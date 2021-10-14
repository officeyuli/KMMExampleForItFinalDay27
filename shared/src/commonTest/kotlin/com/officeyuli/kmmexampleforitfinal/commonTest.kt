package com.officeyuli.kmmexampleforitfinal

import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem
import com.officeyuli.kmmexampleforitfinal.mock.CafeApiMock
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CommonGreetingTest : BaseTest() {
    @Test
    fun KtorTest() {
        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel("""[{"id":"000703fe-cf8a-43c8-bd83-c90cfd61915f","name":"覺旅咖啡","address":"太陽系","city":"taipei"}]"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val apiMock = CafeApiMock(mockEngine)
        val fakeResponseItem =
            CafeResponseItem("000703fe-cf8a-43c8-bd83-c90cfd61915f", "太陽系", "覺旅咖啡", "taipei")
        val result = listOf(fakeResponseItem)
        runTest {
            assertEquals(apiMock.fetchCafeFromApi("taipei"), result)
        }
    }
}
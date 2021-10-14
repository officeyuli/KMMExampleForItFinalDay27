package com.officeyuli.kmmexampleforitfinal

import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem
import com.officeyuli.kmmexampleforitfinal.sqldelight.DatabaseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SQLDelightTest : BaseTest() {

    private lateinit var dbHelper: DatabaseHelper

    private suspend fun DatabaseHelper.insertFakeCafe(id: Int) {
        val fakeList = listOf(CafeResponseItem("fakeId$id", "fakeAddress", "fakeName", "fakeCity"))
        insertCafeList(fakeList)
    }

    @BeforeTest
    fun setup() = runTest {
        dbHelper = DatabaseHelper(
            com.officeyuli.kmmexampleforitfinal.mock.testDbConnection(),
            Dispatchers.Default
        )
        dbHelper.deleteALLCafe()
        dbHelper.insertFakeCafe(0)
    }

    @Test
    fun queryTest() = runTest {
        val CAFEList = dbHelper.selectAllItems().first()
        assertNotNull(CAFEList.find { it.id == "fakeId0" }, "cant find target CAFE")
    }

    @Test
    fun DeleteAllTest() = runTest {
        dbHelper.insertFakeCafe(1)
        dbHelper.insertFakeCafe(2)
        assertTrue(dbHelper.selectAllItems().first().isNotEmpty())
        dbHelper.deleteALLCafe()

        assertTrue(
            dbHelper.selectAllItems().first().count() == 0,
            "delete all Cafe not complete"
        )
    }
}
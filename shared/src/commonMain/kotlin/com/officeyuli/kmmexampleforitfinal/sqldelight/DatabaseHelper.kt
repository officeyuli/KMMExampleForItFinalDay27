package com.officeyuli.kmmexampleforitfinal.sqldelight

import com.officeyuli.kmmexampleforitfinal.CAFE
import com.officeyuli.kmmexampleforitfinal.CafeDB
import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DatabaseHelper(
    sqlDriver: SqlDriver,
    private val backgroundDispatcher: CoroutineDispatcher
) {
    private val cafeDB: CafeDB = CafeDB(sqlDriver)

    fun selectAllItems(): Flow<List<CAFE>> =
        cafeDB.cafeQueries
            .getAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun insertCafeList(cafeList: List<CafeResponseItem>) {
        cafeDB.transactionWithContext(backgroundDispatcher) {
            cafeList.forEach { cafe ->
                cafeDB.cafeQueries.insertCafe(cafe.id, cafe.name, cafe.address)
            }
        }
    }

    suspend fun deleteALLCafe() {
        cafeDB.transactionWithContext(backgroundDispatcher) {
            cafeDB.cafeQueries.deleteAll()
        }
    }
}

internal fun Boolean.toLong(): Long = if (this) 1L else 0L

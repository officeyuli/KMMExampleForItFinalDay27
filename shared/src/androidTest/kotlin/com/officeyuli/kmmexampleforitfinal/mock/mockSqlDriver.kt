package com.officeyuli.kmmexampleforitfinal.mock

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.officeyuli.kmmexampleforitfinal.CafeDB
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

internal actual fun testDbConnection(): SqlDriver {
    val app = ApplicationProvider.getApplicationContext<Application>()
    return AndroidSqliteDriver(CafeDB.Schema, app, "cafedb")
}
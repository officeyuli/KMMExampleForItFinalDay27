package com.officeyuli.kmmexampleforitfinal.mock

import co.touchlab.sqliter.DatabaseConfiguration
import com.officeyuli.kmmexampleforitfinal.CafeDB
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

actual fun testDbConnection(): SqlDriver {
    val schema = CafeDB.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "cafeDB",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}
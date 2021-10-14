package com.officeyuli.kmmexampleforitfinal

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(CafeDB.Schema, get(), "CafeDb")//叫做AndroidSqliteDriver
    }
}
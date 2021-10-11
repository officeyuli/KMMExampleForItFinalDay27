package com.officeyuli.kmmexampleforitfinal.android

import android.app.Application
import android.content.Context
import android.util.Log
import com.officeyuli.kmmexampleforitfinal.DataRepository
import com.officeyuli.kmmexampleforitfinal.initKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class CafeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            module{
                single<Context> {this@CafeApplication}
                viewModel { MainViewModel() }
                single{ DataRepository() }
                single{
                    { Log.e("CafeAndroidApp", "init Koin! " )}
                }

            }
        )
    }
}
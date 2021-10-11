package com.officeyuli.kmmexampleforitfinal

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import platform.Foundation.NSLog
import kotlin.coroutines.CoroutineContext

class IOSMainScope(private val mainContext : CoroutineContext):CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = mainContext +job + exceptionHandler
    private val job = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler{ _,throwable ->
        throwable.printStackTrace()
        showError(throwable)
    }

    private fun showError(throwable: Throwable) {
        NSLog(throwable.message.toString())
    }

    fun onDestroy(){
        job.cancel()
    }
}
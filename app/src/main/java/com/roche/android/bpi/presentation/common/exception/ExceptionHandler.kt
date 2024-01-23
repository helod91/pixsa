package com.roche.android.bpi.presentation.common.exception

import com.roche.android.bpi.presentation.common.arch.SideEffect

interface ExceptionHandler {
    fun parseException(e: Throwable) : SideEffect
}
package com.roche.android.bpi.presentation.common.exception

import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.model.NetworkErrorEffect
import com.roche.android.bpi.presentation.common.model.ShowUnknownErrorDialog
import retrofit2.HttpException

class DefaultExceptionHandler : ExceptionHandler {
    override fun parseException(e: Throwable): SideEffect {
        //TODO check error code or anything else in order to send out a correct side-effect
        //This is just an example
        return if (e is HttpException) {
            when (e.code()) {
                401 -> NetworkErrorEffect.ShowUnauthorizedDialog
                403 -> NetworkErrorEffect.ShowForbiddenDialog
                408 -> NetworkErrorEffect.ShowTimeoutDialog
                else -> ShowUnknownErrorDialog
            }
        } else {
            ShowUnknownErrorDialog
        }
    }
}
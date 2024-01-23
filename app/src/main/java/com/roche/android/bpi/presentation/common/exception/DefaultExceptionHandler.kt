package com.roche.android.bpi.presentation.common.exception

import com.roche.android.bpi.R
import com.roche.android.bpi.presentation.common.model.CommonEffect
import com.roche.android.bpi.presentation.common.arch.SideEffect
import retrofit2.HttpException

class DefaultExceptionHandler : ExceptionHandler {
    override fun parseException(e: Throwable): SideEffect {
        //TODO check error code or anything else in order to send out a correct side-effect
        //This is just an example
        val httpException = e as HttpException
        return when (httpException.code()) {
            401 -> CommonEffect.ShowErrorDialog(R.string.login_again)
            403 -> CommonEffect.ShowErrorDialog(R.string.forbidden)
            408 -> CommonEffect.ShowSnackbar(R.string.timeout)
            else -> CommonEffect.ShowSnackbar(R.string.unknown)
        }
    }
}
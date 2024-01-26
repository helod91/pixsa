package com.roche.android.bpi.presentation.common.exception

import com.roche.android.bpi.R
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.model.CommonEffect
import retrofit2.HttpException

class DefaultExceptionHandler : ExceptionHandler {
    override fun parseException(e: Throwable): SideEffect {
        //TODO check error code or anything else in order to send out a correct side-effect
        //This is just an example
        return if (e is HttpException) {
            when (e.code()) {
                401 -> CommonEffect.ShowErrorDialog(R.string.login_again)
                403 -> CommonEffect.ShowErrorDialog(R.string.forbidden)
                408 -> CommonEffect.ShowSnackbar(R.string.timeout)
                else -> CommonEffect.ShowSnackbar(R.string.unknown)
            }
        } else {
            CommonEffect.ShowSnackbar(R.string.unknown)
        }
    }
}
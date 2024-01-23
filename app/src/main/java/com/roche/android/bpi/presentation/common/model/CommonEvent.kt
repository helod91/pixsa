package com.roche.android.bpi.presentation.common.model

import com.roche.android.bpi.presentation.common.arch.ViewEvent

sealed interface CommonEvent : ViewEvent {
    data object BackClick : CommonEvent
}
package com.roche.android.bpi.presentation.common.arch

interface Reducer {
    operator fun invoke(mutation: Mutation): ViewState?
}
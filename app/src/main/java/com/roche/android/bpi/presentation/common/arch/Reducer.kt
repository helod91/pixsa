package com.roche.android.bpi.presentation.common.arch

interface Reducer<UiState : ViewState> {
    operator fun invoke(mutation: Mutation, currentState: UiState): UiState?
}
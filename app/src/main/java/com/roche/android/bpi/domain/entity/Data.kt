package com.roche.android.bpi.domain.entity

data class Data<ResultData>(
    val result: ResultData? = null,
    val error: Throwable? = null
) {
    companion object {
        fun <ResultData> error(throwable: Throwable?) =
            Data<ResultData>(error = throwable)

        fun <ResultData> success(result: ResultData) =
            Data(result)
    }
}
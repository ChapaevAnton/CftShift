package com.w4eret1ckrtb1tch.homework.ui.utils

import com.w4eret1ckrtb1tch.homework.presentation.model.Result

object ResolveResultHelper {

    fun <TYPE> resolveResult(
        result: Result<TYPE>,
        success: (() -> Unit)?,
        failure: (() -> Unit)?,
        loading: (() -> Unit)?
    ) {
        when (result) {
            is Result.Success -> success?.invoke()
            is Result.Failure -> failure?.invoke()
            is Result.Loading -> loading?.invoke()
        }
    }
}
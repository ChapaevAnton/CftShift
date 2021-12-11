package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.domain.storage.PreferenceStorage
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ReadAuthTokenUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) {

    operator fun invoke(): Flowable<String> {
        return preferenceStorage.readAuthToken()
    }
}
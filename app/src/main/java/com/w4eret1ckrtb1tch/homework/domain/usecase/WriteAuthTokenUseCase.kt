package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.domain.storage.PreferenceStorage
import javax.inject.Inject

class WriteAuthTokenUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) {

    operator fun invoke(authToken: String) {
        preferenceStorage.writeAuthToken(authToken)
    }
}
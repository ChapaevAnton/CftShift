package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import com.w4eret1ckrtb1tch.homework.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostRegisterUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(userAuth: UserAuth): Single<UserEntity> =
        repository.registerUser(userAuth)

}
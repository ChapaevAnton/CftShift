package com.w4eret1ckrtb1tch.homework.domain.repository

import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import io.reactivex.rxjava3.core.Single

interface AuthRepository {

    fun registerUser(userAuth: UserAuth): Single<UserEntity>

    fun loginUser(userAuth: UserAuth): Single<String>
}
package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.datasource.FocusStartApi
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import com.w4eret1ckrtb1tch.homework.domain.mapper.UserResponseMapper
import com.w4eret1ckrtb1tch.homework.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: FocusStartApi,
    private val mapper: UserResponseMapper
) : AuthRepository {

    override fun registerUser(userAuth: UserAuth): Single<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun loginUser(userAuth: UserAuth): Single<String> {
        TODO("Not yet implemented")
    }
}
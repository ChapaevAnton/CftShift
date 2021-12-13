package com.w4eret1ckrtb1tch.homework.data.mapper

import com.w4eret1ckrtb1tch.homework.data.datasource.network.FocusStartApi
import com.w4eret1ckrtb1tch.homework.data.dto.UserResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.UserRole
import com.w4eret1ckrtb1tch.homework.domain.mapper.UserResponseMapper
import javax.inject.Inject

class UserResponseMapperImpl @Inject constructor() : UserResponseMapper {

    override fun mapResponse(userResponse: UserResponse): UserEntity {
        return with(userResponse) {
            UserEntity(
                name = name,
                role = getRole(role)
            )
        }
    }

    private fun getRole(role: String): UserRole {
        return when (role) {
            FocusStartApi.ROLE_ADMIN -> UserRole.ADMIN
            FocusStartApi.ROLE_USER -> UserRole.USER
            else -> UserRole.UNKNOWN
        }
    }
}
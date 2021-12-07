package com.w4eret1ckrtb1tch.homework.domain.mapper

import com.w4eret1ckrtb1tch.homework.data.dto.UserResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity

interface UserResponseMapper {

    fun mapResponse(userResponse: UserResponse): UserEntity
}
package com.w4eret1ckrtb1tch.homework.domain.repository

interface SampleStringRepository {

    fun getFromRemote(): String

    fun getFromLocal(): String
}
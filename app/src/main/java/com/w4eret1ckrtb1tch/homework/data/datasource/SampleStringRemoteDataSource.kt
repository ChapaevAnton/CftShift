package com.w4eret1ckrtb1tch.homework.data.datasource

import javax.inject.Inject

class SampleStringRemoteDataSource @Inject constructor() : SampleStringDataSource {

    private val mockedAnswer = "String from remote data source"

    override fun get(): String = mockedAnswer
}
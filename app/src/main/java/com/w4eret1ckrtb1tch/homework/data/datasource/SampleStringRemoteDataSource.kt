package com.w4eret1ckrtb1tch.homework.data.datasource

class SampleStringRemoteDataSource : SampleStringDataSource {

    private val mockedAnswer = "String from remote data source"

    override fun get(): String = mockedAnswer
}
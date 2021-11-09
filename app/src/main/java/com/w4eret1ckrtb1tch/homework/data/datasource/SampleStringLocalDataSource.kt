package com.w4eret1ckrtb1tch.homework.data.datasource

class SampleStringLocalDataSource : SampleStringDataSource {

    private val mockedCache = "String from local data source"

    override fun get(): String = mockedCache
}
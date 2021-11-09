package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.di.AppScope
import javax.inject.Inject

@AppScope
class SampleStringLocalDataSource @Inject constructor() : SampleStringDataSource {

    private val mockedCache = "String from local data source"

    override fun get(): String = mockedCache
}
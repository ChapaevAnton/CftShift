package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.data.repository.SampleStringRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.repository.SampleStringRepository

class GetSampleStringFromLocalUseCase {

    //TODO: DI
    //TODO: сделать так, чтобы repository не пересоздавался для каждого UseCase
    private val repository: SampleStringRepository = SampleStringRepositoryImpl()

    operator fun invoke(): String {
        val fromLocal = repository.getFromLocal()
        val repoInstanceHash = repository.hashCode()

        return "$fromLocal, repo hash = $repoInstanceHash"
    }
}
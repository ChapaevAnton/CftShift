package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.data.repository.SampleStringRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.repository.SampleStringRepository

class GetSampleStringFromRemoteUseCase {

    //TODO: DI
    //TODO: сделать так, чтобы repository не пересоздавался для каждого UseCase
    private val repository: SampleStringRepository = SampleStringRepositoryImpl()

    operator fun invoke(): String {
        val fromRemote = repository.getFromRemote()
        val repoInstanceHash = repository.hashCode()

        return "$fromRemote, repo hash = $repoInstanceHash"
    }
}
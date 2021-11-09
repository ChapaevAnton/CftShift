package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.domain.repository.SampleStringRepository
import javax.inject.Inject

class GetSampleStringFromLocalUseCase @Inject constructor(
    private val repository: SampleStringRepository
) {

    //TODO: DI OK
    //TODO: сделать так, чтобы repository не пересоздавался для каждого UseCase

    operator fun invoke(): String {
        val fromLocal = repository.getFromLocal()
        val repoInstanceHash = repository.hashCode()

        return "$fromLocal, repo hash = $repoInstanceHash"
    }
}
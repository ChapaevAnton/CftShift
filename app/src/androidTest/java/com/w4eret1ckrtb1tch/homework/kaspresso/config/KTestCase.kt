package com.w4eret1ckrtb1tch.homework.kaspresso.config

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase

abstract class KTestCase(
    builder: Kaspresso.Builder = getBuilder()
) : BaseTestCase<Unit, Unit>(
    kaspressoBuilder = builder,
    dataProducer = { action -> action?.invoke(Unit) }
) {

    private companion object {
        const val FLAKY_SAFETY_TIMEOUT = 3000L

        fun getBuilder(): Kaspresso.Builder =
            Kaspresso.Builder.simple().apply {
                flakySafetyParams.timeoutMs = FLAKY_SAFETY_TIMEOUT
            }
    }

}
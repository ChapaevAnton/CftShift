package com.w4eret1ckrtb1tch.homework

import kotlin.math.cos as kotlinMathCos
import kotlin.math.log as kotlinMathLog
import kotlin.math.sin as kotlinMathSin


class EngineeringImpl : Engineering {
    override fun log(value: Double, base: Double) = kotlinMathLog(value, base)

    override fun log(value: Float, base: Float) = kotlinMathLog(value, base)

    override fun sin(value: Double) = kotlinMathSin(value)

    override fun sin(value: Float) = kotlinMathSin(value)

    override fun cos(value: Double) = kotlinMathCos(value)

    override fun cos(value: Float) = kotlinMathCos(value)
}
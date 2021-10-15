package com.w4eret1ckrtb1tch.homework

class Calculator(private val engineering: Engineering) {

    fun getCos(value: Double) = engineering.cos(value)
    fun getCos(value: Float) = engineering.cos(value)
    fun getSin(value: Double) = engineering.sin(value)
    fun getSin(value: Float) = engineering.sin(value)
    fun getLogarithm(value: Double, base: Double) = engineering.log(value, base)
    fun getLogarithm(value: Float, base: Float) = engineering.log(value, base)
}
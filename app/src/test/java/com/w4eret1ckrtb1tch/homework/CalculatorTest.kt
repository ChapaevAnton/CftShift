package com.w4eret1ckrtb1tch.homework

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.*

class CalculatorTest {


    @Test
    fun `WHEN get logarithm Double data type EXPECT correct answer`() {
        val engineering: Engineering = mock()
        whenever(engineering.log(5.0, 5.0)).thenReturn(1.0)
        val calculator = Calculator(engineering)
        val actual = calculator.getLogarithm(5.0, 5.0)
        val expected = kotlin.math.log(5.0, 5.0)
        assertEquals(expected, actual,0.0000000005)
    }

    @Test
    fun `WHEN get logarithm  Float data type EXPECT correct answer`() {
        val engineering: Engineering = mock()
        whenever(engineering.log(5.0f, 2.0f)).thenReturn(2.3219280949f)
        val calculator = Calculator(engineering)
        val actual = calculator.getLogarithm(5.0f, 2.0f)
        val expected = kotlin.math.log(5.0f, 2.0f)
        assertEquals(expected, actual,0.0000000005f)
    }

}
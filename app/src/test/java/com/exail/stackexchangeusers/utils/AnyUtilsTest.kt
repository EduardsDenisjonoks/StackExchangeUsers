package com.exail.stackexchangeusers.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class AnyUtilsTest {

    private val defaultEmptyValueSymbol = "-"
    private val customEmptyValueSymbol = "---"

    @Test
    fun testNonNullOrBlank_null_expectedDefaultEmptySymbol() {
        val result = null.nonNullOrBlank()

        assertEquals(defaultEmptyValueSymbol, result)
    }

    @Test
    fun testNonNullOrBlank_null_expectedCustomEmptySymbol() {
        val result = null.nonNullOrBlank(customEmptyValueSymbol)

        assertEquals(customEmptyValueSymbol, result)
    }

    @Test
    fun testNonNullOrBlank_validStringValue_expectedSameValue() {
        val validValue = "Location"
        val result = validValue.nonNullOrBlank()

        assertEquals(validValue, result)
    }

    @Test
    fun testNonNullOrBlank_invalidStringValue_expectedSameValue() {
        val invalidValue = ""
        val result = invalidValue.nonNullOrBlank()

        assertEquals(defaultEmptyValueSymbol, result)
    }

    @Test
    fun testNonNullOrBlank_validIntValue_expectedSameValue() {
        val validValue = 33
        val expectedValue = "33"
        val result = validValue.nonNullOrBlank()

        assertEquals(expectedValue, result)
    }

    @Test
    fun testNonNullOrBlank_validLongValue_expectedSameValue() {
        val validValue = 33L
        val expectedValue = "33"
        val result = validValue.nonNullOrBlank()

        assertEquals(expectedValue, result)
    }

    @Test
    fun testNonNullOrBlank_validCharValue_expectedSameValue() {
        val validValue = 'l'
        val expectedValue = "l"
        val result = validValue.nonNullOrBlank()

        assertEquals(expectedValue, result)
    }
}
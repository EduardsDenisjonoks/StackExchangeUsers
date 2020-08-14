package com.exail.stackexchangeusers.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class DateUtilsTest {

    @Test
    fun testEpochTimeToFormattedString_validValue_defaultFormat_expectedFormattedString() {
        val epochTime = 1577836800L //1 January 2020 00:00:00
        val expectedResult = "01 Jan 2020"
        val result = epochTime.epochTimeToFormattedString()

        assertEquals(expectedResult, result)
    }

    @Test
    fun testEpochTimeToFormattedString_zeroValue_defaultFormat_expectedFormattedString() {
        val epochTime = 0L //1 January 1970
        val expectedResult = "01 Jan 1970"
        val result = epochTime.epochTimeToFormattedString()

        assertEquals(expectedResult, result)
    }

    @Test
    fun testEpochTimeToFormattedString_negativeValue_defaultFormat_expectedFormattedString() {
        //TODO consider handling time before 01 Jan 1970
        val epochTime = -1000L //31 December 1969
        val expectedResult = "01 Jan 1970"
        val result = epochTime.epochTimeToFormattedString()

        assertEquals(expectedResult, result)
    }


    @Test
    fun testEpochTimeToFormattedString_validValue_customFormat_expectedCustomFormattedString() {
        val epochTime = 1577836800L //1 January 2020 00:00:00
        val expectedResult = "2020 January 1"
        val result = epochTime.epochTimeToFormattedString(SimpleDateFormat("yyyy MMMM d"))

        assertEquals(expectedResult, result)
    }


}
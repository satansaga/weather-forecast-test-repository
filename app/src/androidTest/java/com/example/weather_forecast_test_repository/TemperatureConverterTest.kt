package com.example.weather_forecast_test_repository

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class TemperatureConverterTest {

    @Test
    fun testToCelsiusText() {
        val expectedList = mutableListOf("37", "37", "37", "38")
        val inputList = mutableListOf(310.0, 310.25, 310.5, 310.75)
        val answerList = mutableListOf<String>()
        for (i in 0..3){
            answerList.add(
                inputList[i].toCelsiusText()
            )
        }
        assertEquals(expectedList, answerList)
    }

    @Test
    fun testToFahrenheitText() {
        val expectedList = mutableListOf("98", "99", "99", "100")
        val inputList = mutableListOf(310.0, 310.25, 310.5, 310.75)
        val answerList = mutableListOf<String>()
        for (i in 0..3){
            answerList.add(
                inputList[i].toFahrenheitText()
            )
        }
        assertEquals(expectedList, answerList)
    }
}
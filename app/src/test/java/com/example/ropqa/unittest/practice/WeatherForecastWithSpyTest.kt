package com.example.ropqa.unittest.practice

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.*

import org.junit.Before
import org.junit.Test

class WeatherForecastWithSpyTest {
    lateinit var weatherForecast: WeatherForecast
    lateinit var formatter: WeatherFormatter

    @Before
    fun setUp() {
        // spy's Method call is same with that of real instance
        formatter = spy(WeatherFormatter())
        val satellite = Satellite()
        val recorder = WeatherRecorder()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @Test
    fun recordCurrentWeather_assertFormatterCalled() {
        weatherForecast.recordCurrentWeather(37.580006, -122.345106)
        verify(formatter, times(1)).format(any())
    }
}
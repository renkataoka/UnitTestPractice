package com.example.ropqa.unittest.practice

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test

class WeatherForcastWithMockRecorderTest {
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder: WeatherRecorder

    @Before
    fun setUp() {
        recorder = mock(name = "MockRecorder")
        val satellite = Satellite()
        val formatter = WeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @Test
    fun recordCurrentWeather_assertRecorderCalled() {
        weatherForecast.recordCurrentWeather(37.580006, -122.345106)
        // verify() : verify Method Called Times of Mock Object
        verify(recorder, times(1)).record(any())

        // argumentCaptor : specify Type you want to capture
        argumentCaptor<Record>().apply {
            // capture() : capture Arguments
            verify(recorder, times(1)).record(capture())
            assertThat(firstValue.description).isEqualTo("Weather is SUNNY")
        }
    }
}
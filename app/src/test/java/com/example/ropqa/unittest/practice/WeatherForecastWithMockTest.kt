package com.example.ropqa.unittest.practice

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.*

import org.junit.After
import org.junit.Before
import org.junit.Test


class WeatherForecastWithMockTest {
    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        satellite = mock(name = "MockSatellite")
        whenever(satellite.getWeather()).thenReturn(Weather.SUNNY)

        val recorder = WeatherRecorder()
        val formatter = WeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }
}
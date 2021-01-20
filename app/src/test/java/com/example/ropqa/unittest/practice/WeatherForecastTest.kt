package com.example.ropqa.unittest.practice

import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test

class WeatherForecastTest {
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder: MockWeatherRecorder

    @Before
    fun setUp() {
        val satellite = Satellite()
        recorder = MockWeatherRecorder()
        weatherForecast = WeatherForecast(satellite, recorder)
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val satellite = StubSatellite(Weather.SUNNY)
        weatherForecast = WeatherForecast(satellite, recorder)

        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }

    @Test
    fun recordCurrentWeather_assertCalled() {
        weatherForecast.recordCurrentWeather()

        val isCalled = recorder.isCalled
        assertThat(isCalled).isTrue()

        val weather = recorder.weather
        assertThat(weather)
            .isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)
    }
}
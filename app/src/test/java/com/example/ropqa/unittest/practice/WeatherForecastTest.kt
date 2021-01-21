package com.example.ropqa.unittest.practice

import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test

class WeatherForecastTest {
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder: MockWeatherRecorder
    lateinit var formatter: SpyWeatherFormatter

    @Before
    fun setUp() {
        val satellite = Satellite()
        recorder = MockWeatherRecorder()
        formatter = SpyWeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val satellite = StubSatellite(Weather.SUNNY)
        weatherForecast = WeatherForecast(satellite, recorder, formatter)

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
            .isEqualTo("Weather is SUNNY")
    }

    @Test
    fun recordCurrentWeather_assertFormatterCalled() {
        weatherForecast.recordCurrentWeather()

        val isCalled = formatter.isCalled
        assertThat(isCalled).isTrue()

        val weather = formatter.weather
        assertThat(weather)
            .isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)
    }
}
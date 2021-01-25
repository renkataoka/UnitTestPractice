package com.example.ropqa.unittest.practice

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException


class WeatherForecastWithMockSatelliteTest {
    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        satellite = mock(name = "MockSatellite")
        // whenever(MockObject,.Method).thenReturn(Return value)
        // any() : Argument matcher for Non-Null
        whenever(satellite.getWeather(any(), any()))
            // invocation : Contains all information and state needed for the Mockito
            .thenAnswer { invocation ->
                val latitude = invocation.arguments[0] as Double
                val longitude = invocation.arguments[1] as Double

                if (latitude in 20.424086..45.550999 &&
                    longitude in 122.933872..153.980789
                ) {
                    return@thenAnswer Weather.SUNNY
                } else {
                    return@thenAnswer Weather.RAINY
                }
            }

        val recorder = WeatherRecorder()
        val formatter = WeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldBringUmbrella_givenInJapan_returnsFalse() {
        val actual = weatherForecast.shouldBringUmbrella(35.669784, 139.817728)
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenBurlingame_returnsTrue() {
        val actual = weatherForecast.shouldBringUmbrella(37.580006, -122.345106)
        assertThat(actual).isTrue()
    }

    @Test
    fun shouldBringUmbrella_throwsException() {
        whenever(satellite.getWeather(any(), any()))
            .thenThrow(RuntimeException("ERROR"))
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy {
                weatherForecast.shouldBringUmbrella(37.580006, -122.345106)
            }
            .withMessage("ERROR")
            .withNoCause()
    }

}
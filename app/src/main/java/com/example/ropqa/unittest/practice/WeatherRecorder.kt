package com.example.ropqa.unittest.practice

open class WeatherFormatter {
    open fun format(weather: Weather): String = "Weather is ${weather}"
}

class SpyWeatherFormatter : WeatherFormatter() {
    var weather: Weather? = null
    var isCalled = false

    override fun format(weather: Weather): String {
        this.weather = weather
        isCalled = true
        return super.format(weather)
    }
}

open class WeatherRecorder {
    open fun record(weather: String) {
        //Do something
    }
}

class MockWeatherRecorder : WeatherRecorder() {
    var weather: String? = null
    var isCalled = false

    override fun record(weather: String) {
        this.weather = weather
        isCalled = true
    }
}
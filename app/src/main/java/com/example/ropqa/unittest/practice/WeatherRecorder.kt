package com.example.ropqa.unittest.practice

open class WeatherRecorder {
    open fun record(weather: Weather) {
        //Do something
    }
}

class MockWeatherRecorder : WeatherRecorder() {
    var weather: Weather? = null
    var isCalled = false

    override fun record(weather: Weather) {
        this.weather = weather
        isCalled = true
    }
}
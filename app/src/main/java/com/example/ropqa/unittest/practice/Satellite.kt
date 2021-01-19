package com.example.ropqa.unittest.practice

open class Satellite {
    open fun getWeather(): Weather {
        return Weather.SUNNY
    }
}

class StubSatellite(val anyWeather: Weather) : Satellite() {
    override fun getWeather(): Weather {
        return anyWeather
    }
}
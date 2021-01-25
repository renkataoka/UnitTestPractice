package com.example.ropqa.unittest.practice

open class Satellite {
    open fun getWeather(latitude: Double, longitude: Double): Weather {
        return Weather.SUNNY
    }
}
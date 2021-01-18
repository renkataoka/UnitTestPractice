package com.example.ropqa.unittest.practice

import java.lang.IllegalArgumentException

class InputChecker {
    fun isValid(text: String?): Boolean {
        if (text == null) throw IllegalArgumentException("Cannot be null!")
        return text.length >= 3 && text.matches(Regex("[a-zA-Z0-9]+"))
    }
}
package com.example.ropqa.unittest.practice

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.InstrumentationRegistry
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JetpackTest {
    @Test
    fun gettingContextTest() {
        val context = InstrumentationRegistry.getTargetContext()
        val appName = context.getString(R.string.app_name)
        assertThat(appName).isEqualTo("UnitTestPractice")
    }
}
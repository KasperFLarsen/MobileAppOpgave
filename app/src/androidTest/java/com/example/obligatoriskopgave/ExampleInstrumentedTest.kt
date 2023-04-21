package com.example.obligatoriskopgave

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.obligatoriskopgave", appContext.packageName)

        Espresso.onView(ViewMatchers.withText("LOG IN"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.edittext_username))
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("gringodingo1995@hotmail.com"))
        Espresso.onView(withId(R.id.edittext_password))
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("hejno123"))
            .perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.button_login)).perform(ViewActions.click())
        pause(2000) // wait for response

    }

    private fun pause(millis: Long) {
        try {
            Thread.sleep(millis)

        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
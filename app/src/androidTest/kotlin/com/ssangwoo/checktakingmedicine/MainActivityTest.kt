package com.ssangwoo.checktakingmedicine

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.isChecked
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ssangwoo.checktakingmedicine.view.main.MainActivity
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by ssangwoo on 2017-07-22.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @Rule @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testAppContext() {
        // Context of the app under test.
        val appContext: Context = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.ssangwoo.checktakingmedicine", appContext.packageName)
    }

    // 스위치 아이디로 (스위치, 텍스트) 변경 테스트
    @Test
    fun testSwitchOnOff() {
        Espresso.onView(withId(R.id.notification_switch))
                .check(ViewAssertions.matches(Matchers.not((isChecked()))))
        Espresso.onView(withId(R.id.notification_switch_text))
                .check(ViewAssertions.matches(withText(R.string.notification_deactive)))

        Espresso.onView(withId(R.id.notification_switch)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.notification_switch))
                .check(ViewAssertions.matches(isChecked()))
        Espresso.onView(withId(R.id.notification_switch_text))
                .check(ViewAssertions.matches(withText(R.string.notification_active)))

        Espresso.onView(withId(R.id.notification_switch)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.notification_switch))
                .check(ViewAssertions.matches(Matchers.not((isChecked()))))
        Espresso.onView(withId(R.id.notification_switch_text))
                .check(ViewAssertions.matches(withText(R.string.notification_deactive)))
    }
}

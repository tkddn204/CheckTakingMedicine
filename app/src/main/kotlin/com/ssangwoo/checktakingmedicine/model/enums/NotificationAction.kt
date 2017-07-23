package com.ssangwoo.checktakingmedicine.model.enums

import com.ssangwoo.checktakingmedicine.R

/**
 * Created by ssangwoo on 2017-07-23.
 */
enum class NotificationAction(val mappingId: Int, val action: String) {
    MORNING_BUTTON_CLICK_ACTION(R.id.button_morning,
            "com.ssangwoo.checktakingmedicine.action.morning_click"),
    AFTERNOON_BUTTON_CLICK_ACTION(R.id.button_afternoon,
            "com.ssangwoo.checktakingmedicine.action.afternoon_click"),
    EVENING_BUTTON_CLICK_ACTION(R.id.button_evening,
            "com.ssangwoo.checktakingmedicine.action.evening_click")
}
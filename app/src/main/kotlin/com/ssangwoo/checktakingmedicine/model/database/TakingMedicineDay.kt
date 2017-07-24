package com.ssangwoo.checktakingmedicine.model.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by ssangwoo on 2017-07-19.
 */

open class TakingMedicineDay(
        @PrimaryKey var id: Long = 0,
        var morning: Boolean = false,
        var afternoon: Boolean = false,
        var evening: Boolean = false,
        var takeMorning: Date? = null,
        var takeAfternoon: Date? = null,
        var takeEvening: Date? = null,
        var currentDay: Date = Date()
): RealmObject()

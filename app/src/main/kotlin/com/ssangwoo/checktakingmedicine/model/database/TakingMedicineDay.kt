package com.ssangwoo.checktakingmedicine.model.database

import io.realm.RealmObject
import java.util.*

/**
 * Created by ssangwoo on 2017-07-19.
 */

open class TakingMedicineDay(
        var morning: Boolean = true,
        var afternoon: Boolean = true,
        var evening: Boolean = true,
        var takeMorning: Date = Date(),
        var takeAfternoonAlarm: Date = Date(),
        var takeNightAlarm: Date = Date()
): RealmObject()

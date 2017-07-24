package com.ssangwoo.checktakingmedicine.model.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by ssangwoo on 2017-07-20.
 */

open class TakingMedicineAlarm(
        @PrimaryKey var id: Long = 0,
        var morningAlarm: Date = Date(),
        var afternoonAlarm: Date = Date(),
        var eveningAlarm: Date = Date(),
        var alarmRepeat: Int = 1
): RealmObject()

package com.ssangwoo.checktakingmedicine.model.database

import io.realm.RealmObject
import java.util.*

/**
 * Created by ssangwoo on 2017-07-20.
 */

open class TakingMedicineAlarm(
        var morningAlarm: Date = Date(),
        var afternoonAlarm: Date = Date(),
        var nightAlarm: Date = Date()
): RealmObject()

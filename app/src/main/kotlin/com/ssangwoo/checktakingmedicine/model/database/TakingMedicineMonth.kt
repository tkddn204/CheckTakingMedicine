package com.ssangwoo.checktakingmedicine.model.database

import io.realm.RealmList
import io.realm.RealmObject
import java.util.*

/**
 * Created by ssangwoo on 2017-07-22.
 */

open class TakingMedicineMonth(
        var todayDate: Date = Date(),
        var memo: String? = null,
        var dayInfoTaking: RealmList<TakingMedicineDay> = RealmList()
): RealmObject()

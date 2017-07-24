package com.ssangwoo.checktakingmedicine.model

import android.util.Log
import com.ssangwoo.checktakingmedicine.model.database.TakingMedicineAlarm
import com.ssangwoo.checktakingmedicine.model.database.TakingMedicineDay
import com.ssangwoo.checktakingmedicine.model.enums.MedicineTime
import io.realm.Realm
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by ssangwoo on 2017-07-24.
 */
class DataBaseHelper private constructor() {
    var realm: Realm by Delegates.notNull()

    private object Holder {
        var INSTANCE = DataBaseHelper()
    }

    fun inItDatabase() {
        realm.executeTransaction {
            if (doesNotExistAlarm()) {
                realm.createObject(TakingMedicineAlarm::class.java, 0)
            }
            if (doesNotExistDay()) {
                realm.createObject(TakingMedicineDay::class.java, 0)
            }
        }
    }

    fun createNextDay() {
        realm.executeTransaction {
            realm.createObject(TakingMedicineDay::class.java)
        }
    }

    fun isTakenMedicineToday(takeWhen: MedicineTime): Boolean {
        val isTakenMedicine: Boolean
        val today = realm.where(TakingMedicineDay::class.java)
                .equalTo("id", realm.where(TakingMedicineDay::class.java)?.max("id")?.toInt())
                .findFirst()
        isTakenMedicine = when (takeWhen) {
            MedicineTime.MORNING -> today.morning
            MedicineTime.AFTERNOON -> today.afternoon
            MedicineTime.EVENING -> today.evening
            else -> false
        }
        return isTakenMedicine
    }

    fun updateTakenOrNotMedicine(medicineTime: MedicineTime) {
        realm.executeTransaction {
            val today = realm.where(TakingMedicineDay::class.java)
                    .equalTo("id", realm.where(TakingMedicineDay::class.java).max("id")?.toInt())
                    .findFirst()
            when (medicineTime) {
                MedicineTime.MORNING -> {
                    today.morning = !today.morning
                    if (today.morning) today.takeMorning = Date()
                    Log.e(today.morning.toString(), today.takeMorning.toString())
                }
                MedicineTime.AFTERNOON -> {
                    today.afternoon = !today.afternoon
                    if (today.afternoon) today.takeAfternoon = Date()
                }
                MedicineTime.EVENING -> {
                    today.evening = !today.evening
                    if (today.evening) today.takeEvening = Date()
                }
                else -> {
                }
            }
        }
    }

    fun removeTakenMedicine() {
        realm.executeTransaction {
            val today = realm.where(TakingMedicineDay::class.java)
                    .equalTo("id", realm.where(TakingMedicineDay::class.java).max("id")?.toInt())
                    .findFirst()
            today.morning = false
            today.afternoon = false
            today.evening = false
            today.takeMorning = null
            today.takeAfternoon = null
            today.takeEvening = null
        }
    }

    fun updateAlarmTime(updateWhen: MedicineTime, updateTime: Long, repeat: Int = 1) {
        realm.executeTransaction {
            val alarm = realm.where(TakingMedicineAlarm::class.java).findFirst()
            when (updateWhen) {
                MedicineTime.MORNING -> alarm.morningAlarm.time = updateTime
                MedicineTime.AFTERNOON -> alarm.afternoonAlarm.time = updateTime
                MedicineTime.EVENING -> alarm.eveningAlarm.time = updateTime
                else -> {
                }
            }
            alarm.alarmRepeat = repeat
        }
    }

    fun doesNotExistAlarm(): Boolean {
        val alarm = realm.where(TakingMedicineAlarm::class.java).findFirst()
        return alarm == null
    }
    fun doesNotExistDay(): Boolean {
        val day = realm.where(TakingMedicineDay::class.java).findFirst()
        return day == null
    }

    companion object {
        val instance: DataBaseHelper by lazy { Holder.INSTANCE }
    }
}
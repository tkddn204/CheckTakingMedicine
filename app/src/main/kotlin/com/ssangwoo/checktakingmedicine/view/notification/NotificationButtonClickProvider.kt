package com.ssangwoo.checktakingmedicine.view.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ssangwoo.checktakingmedicine.R
import com.ssangwoo.checktakingmedicine.model.enums.MedicineTime
import com.ssangwoo.checktakingmedicine.model.enums.NotificationAction
import com.ssangwoo.checktakingmedicine.model.enums.NotificationId
import com.ssangwoo.checktakingmedicine.model.DataBaseHelper

/**
 * Created by ssangwoo on 2017-07-23.
 */
class NotificationButtonClickProvider : BroadcastReceiver() {

    private lateinit var helper: NotificationHelper

    override fun onReceive(context: Context?, intent: Intent?) {
        helper = NotificationHelper(context!!)
        val id: Int
        val medicineTime: MedicineTime
        when (intent?.action) {
            NotificationAction.MORNING_BUTTON_CLICK_ACTION.action
            -> {
                id = NotificationAction.MORNING_BUTTON_CLICK_ACTION.mappingId
                medicineTime = MedicineTime.MORNING
            }
            NotificationAction.AFTERNOON_BUTTON_CLICK_ACTION.action
            -> {
                id = NotificationAction.AFTERNOON_BUTTON_CLICK_ACTION.mappingId
                medicineTime = MedicineTime.AFTERNOON
            }
            NotificationAction.EVENING_BUTTON_CLICK_ACTION.action
            -> {
                id = NotificationAction.EVENING_BUTTON_CLICK_ACTION.mappingId
                medicineTime = MedicineTime.EVENING
            }
            else -> {
                id = 0
                medicineTime = MedicineTime.NOTHING
            }
        }
        val remoteViews = RemoteViewsFactory(context).makeRemoteViews()

        // DB
        if (DataBaseHelper.instance.isTakenMedicineToday(medicineTime)) {
            remoteViews.setTextViewText(id, medicineTime.title)
        } else {
            remoteViews.setTextViewText(id, context.getText(R.string.complete_taking))
        }
        DataBaseHelper.instance.updateTakenOrNotMedicine(medicineTime)
        //

        helper.notify(NotificationId.MAIN.id, helper.getNotification(remoteViews))
    }
}
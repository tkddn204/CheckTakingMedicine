package com.ssangwoo.checktakingmedicine.view.Notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ssangwoo.checktakingmedicine.R
import com.ssangwoo.checktakingmedicine.model.enums.NotificationAction
import com.ssangwoo.checktakingmedicine.model.enums.NotificationId

/**
 * Created by ssangwoo on 2017-07-23.
 */
class NotificationButtonClickProvider : BroadcastReceiver() {

    private lateinit var helper: NotificationHelper

    override fun onReceive(context: Context?, intent: Intent?) {
        helper = NotificationHelper(context!!)
        val id: Int
        when(intent?.action) {
            NotificationAction.MORNING_BUTTON_CLICK_ACTION.action
            -> id = NotificationAction.MORNING_BUTTON_CLICK_ACTION.mappingId
            NotificationAction.AFTERNOON_BUTTON_CLICK_ACTION.action
            -> id = NotificationAction.AFTERNOON_BUTTON_CLICK_ACTION.mappingId
            NotificationAction.EVENING_BUTTON_CLICK_ACTION.action
            -> id = NotificationAction.EVENING_BUTTON_CLICK_ACTION.mappingId
            else -> id = NotificationId.MAIN.id
        }

        val remoteViews = RemoteViewsFactory(context).makeRemoteViews(id)
        remoteViews.setTextViewText(id, context.getText(R.string.complete_taking))

        helper.remove(intent?.extras?.getInt("notificationId")!!)
        helper.notify(id, helper.getNotification(remoteViews))
    }
}
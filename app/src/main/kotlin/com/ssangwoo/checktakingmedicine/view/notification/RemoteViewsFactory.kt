package com.ssangwoo.checktakingmedicine.view.notification

import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.widget.RemoteViews
import com.ssangwoo.checktakingmedicine.R
import com.ssangwoo.checktakingmedicine.model.enums.MedicineTime
import com.ssangwoo.checktakingmedicine.model.enums.NotificationAction

/**
 * Created by ssangwoo on 2017-07-24.
 */
class RemoteViewsFactory(context: Context) : ContextWrapper(context) {

    fun makeRemoteViews(): RemoteViews {
        var remoteViews = RemoteViews(packageName, R.layout.collapsed_notification)

        for (v in NotificationAction.values()) {
            remoteViews = settingRemoteView(
                    remoteViews, v.mappingId,
                    makePendingIntent(v.action))
        }
        return remoteViews
    }

    private fun settingRemoteView(remoteViews: RemoteViews,
                                  id: Int,
                                  pendingButtonIntent: PendingIntent): RemoteViews {
        remoteViews.setOnClickPendingIntent(id, pendingButtonIntent)
        return remoteViews
    }

    private fun makePendingIntent(action: String): PendingIntent {
        val onClickIntent = Intent(this, NotificationButtonClickProvider::class.java)
        onClickIntent.action = action
        return PendingIntent.getBroadcast(
                this, 0, onClickIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}
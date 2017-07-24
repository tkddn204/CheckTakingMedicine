package com.ssangwoo.checktakingmedicine.view.notification

import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews

/**
 * Created by ssangwoo on 2017-07-22.
 */
internal class NotificationHelper(context: Context) : ContextWrapper(context) {

    private val manager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun getNotification(remoteViews: RemoteViews): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext)
                .setCustomContentView(remoteViews)
                //.setCustomBigContentView(expandedNotification)
                .setOngoing(true)
                .setSmallIcon(smallIcon)
    }

    fun notify(id: Int, notification: NotificationCompat.Builder) {
        manager.notify(id, notification.build())
    }

    fun remove(id: Int) {
        manager.cancel(id)
    }

    private val smallIcon: Int
        get() = android.R.drawable.stat_notify_more
}

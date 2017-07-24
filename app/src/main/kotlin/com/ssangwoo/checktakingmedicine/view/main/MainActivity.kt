package com.ssangwoo.checktakingmedicine.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ssangwoo.checktakingmedicine.model.enums.NotificationId
import com.ssangwoo.checktakingmedicine.R
import com.ssangwoo.checktakingmedicine.model.DataBaseHelper
import com.ssangwoo.checktakingmedicine.view.notification.NotificationHelper
import com.ssangwoo.checktakingmedicine.view.notification.RemoteViewsFactory
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var ui: MainUi
    private lateinit var notificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ui = MainUi(activity_main)
        notificationHelper = NotificationHelper(this)
        DataBaseHelper.instance.realm = Realm.getDefaultInstance()
        DataBaseHelper.instance.inItDatabase()
    }

    private fun sendNotification(id: Int) {
        notificationHelper.notify(id, notificationHelper.getNotification(
                RemoteViewsFactory(this).makeRemoteViews()))
    }

    private fun removeNotification(id: Int) {
        DataBaseHelper.instance.removeTakenMedicine()
        notificationHelper.remove(id)
    }


    internal inner class MainUi(root: View): View.OnClickListener {
        init {
            notification_switch.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.notification_switch
                    -> changeStateOfAlarmSwitch(!notification_switch.isChecked)
                else -> Log.e(TAG, "Unknown click event.")
            }
        }

        private fun changeStateOfAlarmSwitch(isChecked: Boolean) {
            if(isChecked) {
                removeNotification(NotificationId.MAIN.id)
                notification_switch_text?.text = getString(R.string.notification_deactive)
            } else {
                sendNotification(NotificationId.MAIN.id)
                notification_switch_text?.text = getString(R.string.notification_active)
            }
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}

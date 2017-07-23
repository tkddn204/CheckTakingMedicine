package com.ssangwoo.checktakingmedicine.view.Main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ssangwoo.checktakingmedicine.model.enums.NotificationId
import com.ssangwoo.checktakingmedicine.R
import com.ssangwoo.checktakingmedicine.view.Notification.NotificationHelper
import com.ssangwoo.checktakingmedicine.view.Notification.RemoteViewsFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var ui: MainUi
    private lateinit var helper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ui = MainUi(activity_main)
        helper = NotificationHelper(this)
    }

    private fun sendNotification(id: Int) {
        helper.notify(id, helper.getNotification(
                RemoteViewsFactory(this).makeRemoteViews(id)))
    }

    private fun removeNotification(id: Int) {
        helper.remove(id)
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

package com.ssangwoo.checktakingmedicine.view

import android.app.Application
import io.realm.Realm
import kotlin.properties.Delegates

/**
 * Created by ssangwoo on 2017-07-19.
 */

class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
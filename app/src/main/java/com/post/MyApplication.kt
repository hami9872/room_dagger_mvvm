package com.post

import android.app.Application
import android.content.Context
import com.post.di.AppComponent
import com.post.di.DaggerAppComponent
import com.post.di.module.RoomDatabaseModule
import com.post.utils.SingletonClass
import org.json.JSONObject

class MyApplication : Application() {
    var singleton: SingletonClass? = null
    private var sInstance: MyApplication? = null

    init {
        instance = this
    }

    lateinit var appComponent: AppComponent

    companion object {
        private var instance: MyApplication? = null
        fun app(): MyApplication {
            return instance as MyApplication
        }

        fun getsAppContext(): Context? {
            return app()!!.sInstance!!.getApplicationContext()
        }
    }

    override fun onCreate() {
        super.onCreate()

        sInstance = this
        singleton = SingletonClass()

        appComponent = DaggerAppComponent
            .builder()
            .roomDatabaseModule(RoomDatabaseModule(this))
            .build()
    }

}
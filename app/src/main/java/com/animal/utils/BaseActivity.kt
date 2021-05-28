package com.animal.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.animal.MyApplication

open class BaseActivity : AppCompatActivity() {
    val mAct = this@BaseActivity
    lateinit var mApp: MyApplication
    lateinit var singleton: SingletonClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mApp = MyApplication.app()
        singleton = SingletonClass.getSingletonInstance()


    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {

    }
}

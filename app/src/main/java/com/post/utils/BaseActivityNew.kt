package com.post.utils

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.post.MyApplication
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivityNew< V : ViewModel?> : DaggerAppCompatActivity() {
    val mAct = this@BaseActivityNew
    lateinit var mApp: MyApplication
    lateinit var singleton: SingletonClass

    private var viewModel: V? = null

    abstract fun getViewModelClass(): V



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = if (viewModel == null) getViewModelClass() else viewModel
        mApp = MyApplication.app()
        singleton = SingletonClass.getSingletonInstance()
    }
}
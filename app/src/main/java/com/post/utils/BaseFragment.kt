package com.post.utils

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.post.MyApplication
import com.google.gson.Gson

open class BaseFragment : Fragment() {
    lateinit var mAct: Activity
    lateinit var mApp: MyApplication
    lateinit var singleton: SingletonClass
    lateinit var gson: Gson
    var mParam1: String? = null
    var mParam2: String? = null
    var mParam3 = 0
    override fun onAttach(context: Context) {
        super.onAttach(context)
        gson = Gson()

        mApp = requireActivity()!!.application as MyApplication
        singleton = SingletonClass.getSingletonInstance()
        mAct = requireActivity()
    }


}
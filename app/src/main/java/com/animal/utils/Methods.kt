package com.animal.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.animal.R


open class Methods : Constants {
    constructor()

    open fun startActivity(mAc: Activity, aClass: Class<*>?, status: Int) {
        if (status == startActivity) {
            mAc.startActivity(Intent(mAc, aClass))
        } else if (status == startActivityWithFinish) {
            mAc.startActivity(Intent(mAc, aClass))
            mAc.finish()
        } else if (status == startActivityWithClearBackStack) {
            mAc.startActivity(
                Intent(
                    mAc,
                    aClass
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        } else if (status == startActivityWithClearTop) {
            mAc.startActivity(Intent(mAc, aClass).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        mAc.overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
    }

    open fun startActivityWithDataBundle(
        mAc: Activity,
        aClass: Class<*>?,
        bundle: Bundle,
        status: Int
    ) {
        if (status == startActivity) {
            mAc.startActivity(Intent(mAc, aClass).putExtras(bundle))
        } else if (status == startActivityWithFinish) {
            mAc.finish()
            mAc.startActivity(Intent(mAc, aClass).putExtras(bundle))
        } else if (status == startActivityWithClearBackStack) {
            mAc.startActivity(
                Intent(mAc, aClass).putExtras(bundle)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        } else if (status == startActivityWithClearTop) {
            mAc.startActivity(
                Intent(mAc, aClass).putExtras(bundle).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
        mAc.overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
//        Animatoo.animateFade(mAc);
    }

    fun finishActivity(mAc: Activity) {
        mAc.finish();
        mAc.overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    companion object {
        fun logMethod(message: String) {
            Log.e(Constants.TAG, message)
        }
    }
}
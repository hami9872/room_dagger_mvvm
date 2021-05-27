package com.post.utils

open class Constants {

    val unknown = -1
    val error = 0
    val success = 1
    val warning = 2
    val default = 3

    val defaultToast = 0
    val toastError = 1
    val toastConfirmed = 2
    val toastInfo = 3

    val IS_UPDATE = "IS_UPDATE"
    val SELECTED_MODEL = "SELECTED_MODEL"

    companion object {
        var DATABASE_NAME = "DATABASE_NAME"
        var TAG = "TAG"

        var NORMAL = 1
        var DELETE = 2
    }

    val startActivity = 0
    val startActivityWithFinish = 1
    val startActivityWithClearBackStack = 2
    val startActivityWithClearTop = 3
    val finishCurrentActivity = 4


}
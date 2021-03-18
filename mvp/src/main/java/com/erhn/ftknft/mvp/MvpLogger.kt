package com.erhn.ftknft.mmvp

import android.util.Log
import com.erhn.ftknft.mmvp.android.MvpBasePresenter

object MvpLogger {

    private var isLogsEnable: Boolean = true

    fun enableLog(isEnable: Boolean) {
        isLogsEnable = isEnable
    }

    internal fun log(tag: String, msg: String) {
        if (isLogsEnable) {
            Log.d(tag, msg)
        }
    }

    internal fun basePresenterLog(msg: String) {
        log(MvpBasePresenter.LOG_TAG, msg)
    }

}
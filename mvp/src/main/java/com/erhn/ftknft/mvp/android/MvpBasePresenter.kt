package com.erhn.ftknft.mmvp.android

import com.erhn.ftknft.mmvp.MvpLogger
import com.erhn.ftknft.mmvp.core.MvpPresenter
import com.erhn.ftknft.mmvp.core.MvpView

abstract class MvpBasePresenter<V : MvpView> : MvpPresenter {

    protected var view: V? = null
        private set

    internal var isSaved: Boolean = false

    internal fun attachView(view: MvpView) {
        this.view = view as V
        onAttachView()
    }

    internal fun detachView() {
        onDetachView()
        this.view = null
    }

    internal fun toClean() {
        onCleaned()
    }


    open fun onAttachView() {
        MvpLogger.basePresenterLog("onAttachView ${javaClass.simpleName}: ${hashCode()}")
    }


    open fun onDetachView() {
        MvpLogger.basePresenterLog("onDetachView ${javaClass.simpleName}: ${hashCode()}")
    }

    protected open fun onCleaned() {
        MvpLogger.basePresenterLog("onCleaned ${javaClass.simpleName}: ${hashCode()}")
    }


    companion object {
        internal const val LOG_TAG = "MVP_PRESENTER"
    }
}
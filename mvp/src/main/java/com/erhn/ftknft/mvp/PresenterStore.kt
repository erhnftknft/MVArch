package com.erhn.ftknft.mmvp

import com.erhn.ftknft.mmvp.android.MvpBasePresenter
import com.erhn.ftknft.mmvp.core.MvpPresenter
import com.erhn.ftknft.mmvp.core.MvpView


internal object PresenterStore {

    private val presenters = HashMap<String, MvpPresenter>()

    fun save(viewId: String, presenter: MvpPresenter) {
        (presenter as? MvpBasePresenter<*>)?.isSaved = true
        presenters.put(viewId, presenter)
    }

    fun <V : MvpView, P : MvpBasePresenter<V>> restore(viewId: String): P? {
        val mvpBasePresenter = presenters.get(viewId) as? P
        return mvpBasePresenter?.apply {
            presenters.remove(viewId)
            isSaved = false
        }
    }
}
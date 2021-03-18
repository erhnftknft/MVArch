package com.erhn.ftknft.mmvp.android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.erhn.ftknft.mmvp.PresenterStore
import com.erhn.ftknft.mmvp.core.MvpView
import java.util.*

abstract class MvpBaseDialogFragment<V : MvpView, P : MvpBasePresenter<V>> : DialogFragment(),
    MvpView {

    private val keyViewId = "com.erhn.ftknft.mvp.dialogfragment.keyviewid"

    private lateinit var viewId: String

    protected lateinit var presenter: P
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewId = savedInstanceState?.getString(keyViewId) ?: UUID.randomUUID().toString()
        presenter = PresenterStore.restore<V, P>(viewId) ?: initPresenter()
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        onViewInit(view, savedInstanceState)
    }

    abstract fun onViewInit(view: View, savedInstanceState: Bundle?)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(keyViewId, viewId)
        PresenterStore.save(viewId, presenter)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!presenter.isSaved) {
            presenter.toClean()
        }
    }

    protected abstract fun initPresenter(): P
}
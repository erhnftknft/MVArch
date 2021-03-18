package com.erhn.ftknft.mmvp.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.erhn.ftknft.mmvp.PresenterStore
import com.erhn.ftknft.mmvp.core.MvpView
import java.util.*

abstract class MvpBaseActivity<V : MvpView, P : MvpBasePresenter<V>> : AppCompatActivity(),
    MvpView {

    private val keyViewId = "com.erhn.ftknft.mvp.activity.keyviewid"

    private lateinit var viewId: String

    protected abstract val layout: Int

    protected lateinit var presenter: P
        private set

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewId = savedInstanceState?.getString(keyViewId) ?: UUID.randomUUID().toString()
        presenter = PresenterStore.restore<V, P>(viewId) ?: initPresenter()
        setContentView(layout)
        presenter.attachView(this)
        onViewInit(savedInstanceState)
    }

    abstract fun onViewInit(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        if (!presenter.isSaved) {
            presenter.toClean()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(keyViewId, viewId)
        PresenterStore.save(viewId, presenter)
    }


    abstract fun initPresenter(): P


}
package com.javiermp.marvelheroes.webview.viewmodel

import com.javiermp.marvelheroes.common.viewmodel.CommonEventsViewModel
import io.reactivex.disposables.CompositeDisposable

class WebviewViewModel() : CommonEventsViewModel() {

    lateinit var url: String
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
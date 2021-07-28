package com.javiermp.marvelheroes.common.widget.error

import com.javiermp.marvelheroes.common.errorhandling.ErrorBundle

interface ErrorListener {

    fun onRetry(errorBundle: ErrorBundle)
}
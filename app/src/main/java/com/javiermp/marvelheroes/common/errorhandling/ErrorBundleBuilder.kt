package com.javiermp.marvelheroes.common.errorhandling

interface ErrorBundleBuilder {

    fun build(throwable: Throwable, appAction: AppAction): ErrorBundle
}
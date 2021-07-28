package com.javiermp.marvelheroes.common.extensions

import android.view.View
import com.javiermp.marvelheroes.common.view.OnSingleClickListener

fun View.setOnSingleClickListener(listener: (View) -> Unit) {
    this.setOnClickListener(
        OnSingleClickListener.wrap(View.OnClickListener(listener))
    )
}
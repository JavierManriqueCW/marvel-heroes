package com.javiermp.marvelheroes.common.errorhandling

import android.content.Context
import com.javiermp.marvelheroes.R

object ErrorUtils {

    fun buildErrorMessageForDialog(context: Context, errorBundle: ErrorBundle): ErrorMessageForDialog {
        return ErrorMessageForDialog(
            context.getString(R.string.error_dialog_title),
            context.getString(errorBundle.stringId),
            errorBundle.debugMessage,
            errorBundle.appAction.code,
            errorBundle.appError.code
        )
    }
}
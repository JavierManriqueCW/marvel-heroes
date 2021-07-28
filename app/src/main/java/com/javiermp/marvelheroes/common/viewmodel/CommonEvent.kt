package com.javiermp.marvelheroes.common.viewmodel

sealed class CommonEvent {

    object Unauthorized : CommonEvent()
}
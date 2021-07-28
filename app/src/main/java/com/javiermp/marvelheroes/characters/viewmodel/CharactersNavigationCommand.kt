package com.javiermp.marvelheroes.characters.viewmodel

sealed class CharactersNavigationCommand {
    object GoToDetail : CharactersNavigationCommand()
}
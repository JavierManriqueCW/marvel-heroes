package com.javiermp.marvelheroes.common.di

import com.javiermp.data.characters.repository.CharactersDataRepository
import com.javiermp.data.characters.source.CharactersMemoryDataStore
import com.javiermp.data.characters.source.CharactersRemoteDataStore
import com.javiermp.datasources.characters.memory.CharactersMemoryImplementation
import com.javiermp.datasources.characters.remote.mapper.CharactersRemoteMapper
import com.javiermp.datasources.characters.memory.Memory
import com.javiermp.datasources.characters.remote.CharactersRemoteImplementation
import com.javiermp.datasources.remote.provider.GsonProvider
import com.javiermp.datasources.characters.remote.MarvelHeroesServiceFactory
import com.javiermp.domain.characters.interactor.GetCharacterDetail
import com.javiermp.domain.characters.interactor.GetCharacters
import com.javiermp.domain.characters.repository.CharactersRepository
import com.javiermp.domain.executor.JobExecutor
import com.javiermp.domain.executor.PostExecutionThread
import com.javiermp.domain.executor.ThreadExecutor
import com.javiermp.marvelheroes.BuildConfig
import com.javiermp.marvelheroes.common.errorhandling.ErrorBundleBuilder
import com.javiermp.marvelheroes.common.viewmodel.UiThread
import com.javiermp.marvelheroes.characters.viewmodel.CharactersErrorBuilder
import com.javiermp.marvelheroes.characters.viewmodel.CharactersViewModel
import com.javiermp.marvelheroes.webview.viewmodel.WebviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UiThread() }
    single { GsonProvider.makeGson() }
    single { Memory() }

    // Characters
    factory { CharactersRemoteMapper() }
    factory { MarvelHeroesServiceFactory.makeMarvelHeroesServiceFactory(BuildConfig.DEBUG, get()) }
    factory<CharactersRemoteDataStore>(named("charactersDataStoreRemote")) { CharactersRemoteImplementation(get(), get()) }
    factory<CharactersMemoryDataStore>(named("charactersDataStoreMemory")) { CharactersMemoryImplementation(get()) }
    factory { CharactersDataRepository(get(named("charactersDataStoreRemote")), get(named("charactersDataStoreMemory"))) }
    factory<CharactersRepository> {
        CharactersDataRepository(
            get(named("charactersDataStoreRemote")),
            get(named("charactersDataStoreMemory"))
        )
    }
}

val charactersModule = module(override = true) {
    factory<ErrorBundleBuilder>(named("charactersErrorBuilder")) { CharactersErrorBuilder() }
    factory { GetCharacters(get(), get(), get()) }
    factory { GetCharacterDetail(get(), get(), get()) }
    viewModel { CharactersViewModel(get(), get(), get(named("charactersErrorBuilder"))) }
}

val webviewModule = module(override = true) {
    viewModel { WebviewViewModel() }
}
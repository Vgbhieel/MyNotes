package me.vitornascimento.mynotes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vitornascimento.mynotes.data.adapter.NotesAdapter
import me.vitornascimento.mynotes.domain.port.NotesPort

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun bindNotesPort(
        adapter: NotesAdapter,
    ): NotesPort

}
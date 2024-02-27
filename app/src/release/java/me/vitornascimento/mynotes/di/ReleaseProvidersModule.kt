package me.vitornascimento.mynotes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.vitornascimento.mynotes.data.adapter.NotesDataBase

@Module
@InstallIn(SingletonComponent::class)
object ReleaseProvidersModule {

    @Provides
    fun provideNotesDatabase(
        @ApplicationContext context: Context,
    ): NotesDataBase =
        Room.databaseBuilder(
            context = context,
            klass = NotesDataBase::class.java,
            name = "notes_database"
        )
            .build()

    @Provides
    fun provideNotesDao(notesDataBase: NotesDataBase) = notesDataBase.getNotesDao()

}
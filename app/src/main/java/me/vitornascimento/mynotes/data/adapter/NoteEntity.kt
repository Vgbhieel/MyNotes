package me.vitornascimento.mynotes.data.adapter

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = -1,
    val title: String,
    val content: String,
)
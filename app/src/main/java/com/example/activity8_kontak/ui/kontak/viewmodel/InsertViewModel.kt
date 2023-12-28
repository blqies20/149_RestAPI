package com.example.activity8_kontak.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activity8_kontak.model.Kontak
import com.example.activity8_kontak.repository.KontakRepository
import kotlinx.coroutines.launch

class InsertViewModel (private val kontakRepository: KontakRepository) : ViewModel() {

    var insertKontakState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertKontakState(insertUiEvent : InsertUiEvent) {
        insertKontakState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertKontak() {
        viewModelScope.launch {
            try {
                kontakRepository.insertKontak(insertKontakState.insertUiEvent.toKontak())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id: Int = 0,
    val nama: String = "",
    val email: String = "",
    val telepon: String = ""
)

fun InsertUiEvent.toKontak(): Kontak = Kontak(
    id = id,
    nama = nama,
    email = email,
    telepon = telepon
)

fun Kontak.toUiStateKontak(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Kontak.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id = id,
    nama = nama,
    email = email,
    telepon = telepon
)


package com.w4eret1ckrtb1tch.homework.presentation

sealed class MainState {

    object Loading : MainState()

    data class Success(val remoteString: String, val localString: String) : MainState()
}
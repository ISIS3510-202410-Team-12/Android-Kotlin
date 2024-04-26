package com.example.kotlin.Helper

sealed class ConnectionStatus {
    object Available : ConnectionStatus()

    object Unavailable : ConnectionStatus()
}
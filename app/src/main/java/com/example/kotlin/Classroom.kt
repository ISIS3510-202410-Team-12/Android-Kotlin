package com.example.kotlin

data class Classroom(
    val building: String,
    val room: String,
    val availableFrom: String,
    val availableUntil: String,
    val minutesAvailable: Int
)
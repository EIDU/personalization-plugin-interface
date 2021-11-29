package com.eidu.personalization

import java.time.Duration

data class UnitOutcome(
    val unitId: String,
    val duration: Duration,
    val result: UnitResult,
    val score: Float? = null
)

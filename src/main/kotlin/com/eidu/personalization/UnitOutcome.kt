package com.eidu.personalization

import java.time.Duration
import java.time.Instant

data class UnitOutcome(
    val unitId: String,
    val time: Instant,
    val duration: Duration,
    val result: UnitResult,
    val score: Float? = null
)

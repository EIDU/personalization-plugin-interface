package com.eidu.personalization

import java.time.Duration
import java.util.UUID

data class UnitOutcome(
    val runId: UUID,
    val curriculumId: String,
    val lessonId: String,
    val contentId: String,
    val sessionId: UUID,
    val result: UnitResult,
    val duration: Duration,
    val foregroundDuration: Duration? = null,
    val sessionTimeAtStart: Duration? = null,
    val foregroundSessionTimeAtStart: Duration? = null,
    val score: Float? = null
)

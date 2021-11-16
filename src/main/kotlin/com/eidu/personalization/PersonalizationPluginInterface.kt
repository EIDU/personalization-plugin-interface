package com.eidu.personalization

import java.io.File
import java.time.Duration
import java.util.UUID

interface PersonalizationPlugin {
    fun determineNextContentIds(
        input: PersonalizationInput,
        tensorflowModelInferenceRunner: ((modelFile: File, input: Any, output: Any) -> Unit)? = null
    ): PersonalizationOutput
}

data class PersonalizationInput(val learnerProgress: List<UnitOutcome>, val possibleContentIds: List<String>)

data class PersonalizationOutput(val nextContentIds: List<String>, val processedContentIds: Map<String, Float>)

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

enum class UnitResult {
    Success,
    Abort,
    Error,
    TimeoutInactivity,
    TimeUp
}

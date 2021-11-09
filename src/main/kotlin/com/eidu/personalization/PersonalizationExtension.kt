package com.eidu.personalization

import com.eidu.content.reporting.RunWu
import org.pf4j.ExtensionPoint
import java.io.File

typealias UnitOutcome = RunWu

interface PersonalizationPlugin : ExtensionPoint {
    fun determineNextContentIds(
        input: PersonalizationInput,
        tensorflowModelInferenceRunner: ((modelFile: File, input: Any) -> (Any))? = null
    ): PersonalizationOutput
}

data class PersonalizationInput(val progress: List<UnitOutcome>, val contentIds: List<String>)

data class UnitProbability(val contentId: String, val probability: Float)

data class PersonalizationOutput(val nextContentIds: List<String>, val probabilities: List<UnitProbability>)

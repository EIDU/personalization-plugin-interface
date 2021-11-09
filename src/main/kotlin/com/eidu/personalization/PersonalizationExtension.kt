package com.eidu.personalization

import com.eidu.content.reporting.RunWu
import org.pf4j.ExtensionPoint
import java.io.File

interface PersonalizationPlugin : ExtensionPoint {
    fun determineNextWu(
        input: PersonalizationInput,
        tensorflowModelInferenceRunner: ((modelFile: File, input: Any) -> (Any))? = null
    ): PersonalizationOutput
}

data class PersonalizationInput(val progress: List<RunWu>, val contentIds: List<String>)

data class WuProbability(val contentId: String, val probability: Float)

data class PersonalizationOutput(val nextContentIds: List<String>, val probabilities: List<WuProbability>)

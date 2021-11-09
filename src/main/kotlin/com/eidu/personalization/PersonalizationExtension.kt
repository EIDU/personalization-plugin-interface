package com.eidu.personalization

import com.eidu.content.reporting.RunWu
import org.pf4j.ExtensionPoint

interface PersonalizationPlugin : ExtensionPoint {
    fun determineNextWu(input: PersonalizationInput): PersonalizationOutput
}
data class PersonalizationInput(val progress: List<RunWu>, val contentIds: List<String>)

data class WuProbability(val contentId: String, val probability: Float)

data class PersonalizationOutput(val nextContentId: String, val probabilities: List<WuProbability>)

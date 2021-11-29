package com.eidu.personalization

import java.io.File

interface PersonalizationPlugin {
    fun determineNextContentIds(
        input: PersonalizationInput,
        tensorflowModelInferenceRunner: ((modelFile: File, input: Any, output: Any) -> Unit)? = null
    ): PersonalizationOutput
}

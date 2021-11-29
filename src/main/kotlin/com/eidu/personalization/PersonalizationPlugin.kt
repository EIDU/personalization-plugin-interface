package com.eidu.personalization

import java.io.File

interface PersonalizationPlugin {
    fun determineNextContentIds(
        input: PersonalizationInput,
        runTensorflowInference: (modelFile: File, input: Any, output: Any) -> Unit
    ): PersonalizationOutput
}

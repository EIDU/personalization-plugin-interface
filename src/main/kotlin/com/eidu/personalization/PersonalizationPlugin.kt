package com.eidu.personalization

interface PersonalizationPlugin {
    fun determineNextContentIds(
        input: PersonalizationInput,
        runTensorflowInference: (modelFileResourcePath: String, input: Any, output: Any) -> Unit
    ): PersonalizationOutput
}

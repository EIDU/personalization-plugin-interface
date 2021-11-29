package com.eidu.personalization

interface PersonalizationPlugin {
    fun determineNextUnits(
        input: PersonalizationInput,
        runTensorflowInference: (modelFileResourcePath: String, input: Any, output: Any) -> Unit
    ): PersonalizationOutput
}

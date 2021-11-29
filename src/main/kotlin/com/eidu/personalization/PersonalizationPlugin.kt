package com.eidu.personalization

interface PersonalizationPlugin {
    fun determineNextUnits(
        input: PersonalizationInput,
        runTensorflowInference: (modelResourcePath: String, input: Any, output: Any) -> Unit
    ): PersonalizationOutput
}

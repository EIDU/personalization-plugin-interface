package com.eidu.personalization.api;

import org.jetbrains.annotations.NotNull;

public interface PersonalizationPlugin {
    PersonalizationOutput determineNextUnits(
        @NotNull PersonalizationInput input,
        @NotNull TensorflowInferenceRunner tensorflowModelInferenceRunner
    );
}

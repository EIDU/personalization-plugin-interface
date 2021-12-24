package com.eidu.personalization.api;

public interface TensorflowInferenceRunner {
    void infer(String modelResourcePath, Object input, Object output);
}

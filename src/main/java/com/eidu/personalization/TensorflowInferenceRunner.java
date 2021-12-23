package com.eidu.personalization;

public interface TensorflowInferenceRunner {
    void infer(String modelResourcePath, Object input, Object output);
}

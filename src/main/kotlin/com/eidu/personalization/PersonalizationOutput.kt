package com.eidu.personalization

data class PersonalizationOutput(val nextContentIds: List<String>, val processedContentIds: Map<String, Float>)

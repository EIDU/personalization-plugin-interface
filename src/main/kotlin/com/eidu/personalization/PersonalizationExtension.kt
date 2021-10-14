package com.eidu.personalization

import org.pf4j.ExtensionPoint

interface PersonalizationPlugin : ExtensionPoint {
    fun determineNextWu(input: PersonalizationInput): PersonalizationOutput
}

data class WuProgress(val contentId: String, val outcome: WuOutcome, val score: Float?)

enum class WuOutcome {
    Timeout, // no user interaction has happened for a defined period of time
    Aborted, // the user aborted the activity
    Guessed, // the user performed at least one action deemed incorrect
    ConsecutiveMistakes, // the user made consecutive mistakes
    ConsecutiveTimeouts, // the user made consecutive timeouts
    UsedHints, // the user, having performed no incorrect actions, triggered the hinting mechanism
    Solved, // the user played the activity till the end, performing no incorrect actions
    Suspended, // the application was suspended during the activity
    LongBreak, // no user interaction or the application was suspended for a particularly long time
    Quit, // the application was terminated during the activity
    Error; // the activity ended because of an unhandled exception
}

data class PersonalizationInput(val progress: List<WuProgress>, val contentIds: List<String>)

data class WuProbability(val contentId: String, val probability: Float)

data class PersonalizationOutput(val nextContentId: String, val probabilities: List<WuProbability>)

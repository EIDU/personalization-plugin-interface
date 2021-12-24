package com.eidu.personalization.api;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class PersonalizationInput {
    @NotNull
    public final List<UnitResult> learningHistory;
    @NotNull
    public final List<String> availableUnits;

    public PersonalizationInput(@NotNull List<UnitResult> learningHistory, @NotNull List<String> availableUnits) {
        this.learningHistory = learningHistory;
        this.availableUnits = availableUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalizationInput that = (PersonalizationInput) o;
        return learningHistory.equals(that.learningHistory) && availableUnits.equals(that.availableUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(learningHistory, availableUnits);
    }
}

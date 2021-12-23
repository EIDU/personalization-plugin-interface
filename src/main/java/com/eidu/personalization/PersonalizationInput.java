package com.eidu.personalization;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class PersonalizationInput {
    @NotNull
    public final List<UnitOutcome> learningHistory;
    @NotNull
    public final List<String> availableUnits;

    public PersonalizationInput(@NotNull List<UnitOutcome> learningHistory, @NotNull List<String> availableUnits) {
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

package com.eidu.personalization;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PersonalizationOutput {
    @NotNull
    public final List<String> nextUnits;
    @NotNull
    public final Map<String, Float> processedUnits;

    public PersonalizationOutput(
            @NotNull List<String> nextUnits,
            @NotNull Map<String, Float> processedUnits
    ) {
        this.nextUnits = nextUnits;
        this.processedUnits = processedUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalizationOutput that = (PersonalizationOutput) o;
        return nextUnits.equals(that.nextUnits) && processedUnits.equals(that.processedUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextUnits, processedUnits);
    }
}

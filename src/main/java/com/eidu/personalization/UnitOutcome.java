package com.eidu.personalization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.Objects;

public class UnitOutcome {
    @NotNull
    public final String unitId;
    @NotNull
    public final UnitResult result;
    @NotNull
    public final Duration duration;
    @Nullable
    public final Float score;

    public UnitOutcome(
        @NotNull String unitId,
        @NotNull UnitResult result,
        @NotNull Duration duration,
        @Nullable Float score
    ) {
        this.unitId = unitId;
        this.result = result;
        this.duration = duration;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitOutcome that = (UnitOutcome) o;
        return unitId.equals(that.unitId) && result == that.result && duration.equals(that.duration) &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitId, result, duration, score);
    }
}

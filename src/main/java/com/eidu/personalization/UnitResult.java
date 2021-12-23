package com.eidu.personalization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.Objects;

public class UnitResult {
    @NotNull
    public final String unitId;
    @NotNull
    public final UnitResultType resultType;
    @NotNull
    public final Duration duration;
    @Nullable
    public final Float score;

    public UnitResult(
        @NotNull String unitId,
        @NotNull UnitResultType resultType,
        @NotNull Duration duration,
        @Nullable Float score
    ) {
        this.unitId = unitId;
        this.resultType = resultType;
        this.duration = duration;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitResult that = (UnitResult) o;
        return unitId.equals(that.unitId) && resultType == that.resultType && duration.equals(that.duration) &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitId, resultType, duration, score);
    }
}

package com.eidu.personalization.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class UnitResult {
    @NotNull
    public final String unitId;
    @NotNull
    public final UnitResultType resultType;
    public final long durationMs;
    @Nullable
    public final Float score;

    public UnitResult(
        @NotNull String unitId,
        @NotNull UnitResultType resultType,
        long durationMs,
        @Nullable Float score
    ) {
        this.unitId = unitId;
        this.resultType = resultType;
        this.durationMs = durationMs;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitResult that = (UnitResult) o;
        return unitId.equals(that.unitId) && resultType == that.resultType && durationMs == that.durationMs &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitId, resultType, durationMs, score);
    }
}

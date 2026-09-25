package dev.practice.foundations.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ItemRequest(
        @NotBlank @Size(max = 80) String name,
        @NotNull @PositiveOrZero BigDecimal price) {
}

package com.dyma.tennis;

import jakarta.validation.constraints.NotNull;

public record UserCredentials(
        @NotNull(message = "Login is mandatory") String login,
        @NotNull(message = "Password is mandatory") String password) {
}

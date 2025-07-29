package com.dyma.tennis.service;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String lastName) {
        super("player not found with name " + lastName);
    }
}

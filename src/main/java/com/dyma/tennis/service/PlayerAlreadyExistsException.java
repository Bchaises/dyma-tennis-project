package com.dyma.tennis.service;

public class PlayerAlreadyExistsException extends RuntimeException{
    public PlayerAlreadyExistsException(String lastName) {
        super("player already exists with last name " + lastName);
    }
}

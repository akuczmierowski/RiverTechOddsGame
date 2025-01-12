package pl.andrzejkuczmierowski.RiverTechOddsGame.service;

public class PlayerException extends Exception{
    public PlayerException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PlayerException(String message) {
        super(message);
    }
}

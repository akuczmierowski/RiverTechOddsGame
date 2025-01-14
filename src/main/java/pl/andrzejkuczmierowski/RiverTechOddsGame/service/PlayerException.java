package pl.andrzejkuczmierowski.RiverTechOddsGame.service;

//TODO create base exception and make PlayerException extend it
public class PlayerException extends RuntimeException{
    public PlayerException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PlayerException(String message) {
        super(message);
    }
}

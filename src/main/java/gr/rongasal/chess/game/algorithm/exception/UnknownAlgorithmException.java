package gr.rongasal.chess.game.algorithm.exception;

import gr.rongasal.chess.game.GameException;
import gr.rongasal.chess.game.algorithm.SearchAlgorithmType;

public class UnknownAlgorithmException extends GameException {
    public UnknownAlgorithmException(SearchAlgorithmType searchAlgorithmType) {
        super(String.format("Unknown algorithm %s.", searchAlgorithmType));
    }
}

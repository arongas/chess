package gr.rongasal.chess.game.pieces.exception;

import gr.rongasal.chess.game.GameException;
import gr.rongasal.chess.game.pieces.PieceType;

public class UnsupportedPieceTypeException extends GameException {
    public UnsupportedPieceTypeException(PieceType pieceType) {
        super(String.format("Unsupported connectionProvider type %s. Cannot construct item", pieceType));
    }
}

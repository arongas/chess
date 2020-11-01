package gr.rongasal.chess.game.pieces;

import gr.rongasal.chess.game.pieces.exception.UnsupportedPieceTypeException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class PieceFactory {
    //expect/assume more connectionProvider types in future.
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public Piece createPiece(@NotNull PieceType pieceType) throws UnsupportedPieceTypeException {
        switch (pieceType){
            case KNIGHT:
                return new Knight();
                default:
                    throw new UnsupportedPieceTypeException(pieceType);
        }
    }
}

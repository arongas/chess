package gr.rongasal.chess.dto;

import gr.rongasal.chess.game.ChessCell;
import gr.rongasal.chess.game.algorithm.SearchAlgorithmType;
import gr.rongasal.chess.game.pieces.PieceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolutionNotFoundResponse extends GameResponse {
    private String message;

    private SolutionNotFoundResponse(SearchAlgorithmType searchAlgorithmType, PieceType pieceType, ChessCell start, ChessCell destination, int depth, String message) {
        super(searchAlgorithmType, pieceType, start, destination, depth);
        this.message = message;
    }

    public SolutionNotFoundResponse(GameRequest gameRequest, String message) {
        this(gameRequest.getSearchAlgorithmType(), gameRequest.getPieceType(), gameRequest.getStart(), gameRequest.getDestination(), gameRequest.getDepth(), message);
    }
}

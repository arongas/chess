package gr.rongasal.chess.dto;

import gr.rongasal.chess.game.ChessCell;
import gr.rongasal.chess.game.algorithm.SearchAlgorithmType;
import gr.rongasal.chess.game.pieces.PieceType;

public class GameResponse extends GameRequest {

    GameResponse(SearchAlgorithmType searchAlgorithmType, PieceType pieceType, ChessCell start, ChessCell destination, int depth) {
        super(searchAlgorithmType, pieceType, start, destination, depth);
    }
}

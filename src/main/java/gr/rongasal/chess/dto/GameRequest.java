package gr.rongasal.chess.dto;

import gr.rongasal.chess.game.ChessCell;
import gr.rongasal.chess.game.algorithm.SearchAlgorithmType;
import gr.rongasal.chess.game.pieces.PieceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameRequest {
    @NotNull
    private SearchAlgorithmType searchAlgorithmType;
    @NotNull
    private PieceType pieceType;
    @NotNull
    private ChessCell start;
    @NotNull
    private ChessCell destination;

    @Min(0)
    @Max(6)
    private int depth;
}

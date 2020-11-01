package gr.rongasal.chess.game.pieces;

import gr.rongasal.chess.game.ChessCell;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Knight implements Piece {

    @Override
    public List<ChessCell> next(ChessCell from) {
        return Stream.of(
                new ChessCell(from.getPosition_x() + 2, from.getPosition_y() + 1),
                new ChessCell(from.getPosition_x() + 2, from.getPosition_y() - 1),
                new ChessCell(from.getPosition_x() - 2, from.getPosition_y() + 1),
                new ChessCell(from.getPosition_x() - 2, from.getPosition_y() - 1),
                new ChessCell( from.getPosition_x() + 1, from.getPosition_y() + 2),
                new ChessCell( from.getPosition_x() - 1, from.getPosition_y() + 2),
                new ChessCell( from.getPosition_x() + 1, from.getPosition_y() - 2),
                new ChessCell( from.getPosition_x() - 1, from.getPosition_y() - 2)
        ).filter(ChessCell::isValid).collect(Collectors.toList());
    }

}
